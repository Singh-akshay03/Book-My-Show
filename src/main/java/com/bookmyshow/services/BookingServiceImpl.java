package com.bookmyshow.services;

import com.bookmyshow.exceptions.ShowNotFoundException;
import com.bookmyshow.exceptions.ShowSeatIsNotAvailable;
import com.bookmyshow.exceptions.UserNotFoundException;
import com.bookmyshow.models.*;
import com.bookmyshow.repositories.ShowRepository;
import com.bookmyshow.repositories.ShowSeatRepository;
import com.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookingServiceImpl implements IBookingService {
    private final UserRepository userRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    private final CalculatePrice calculatePrice;

    public BookingServiceImpl(UserRepository userRepository, ShowSeatRepository showSeatRepository, ShowRepository showRepository, CalculatePrice calculatePrice) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.calculatePrice = calculatePrice;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException, ShowNotFoundException, ShowSeatIsNotAvailable {
        User user = getUserById(userId);
        Show show = getShowById(showId);
        List<ShowSeat> showSeats = getShowSeatsByIds(showSeatIds);
        validateShowSeatsAvailability(showSeats);
        double totalPrice = calculateAndBlockSeats(showSeats, show.getBasePrice());

        return createAndSaveBooking(user, show, showSeats, totalPrice);
    }

    private User getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("USER WITH THE GIVEN ID: " + userId + " IS NOT FOUND"));
    }

    private Show getShowById(Long showId) throws ShowNotFoundException {
        return showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("SHOW WITH THE GIVEN ID: " + showId + " IS NOT FOUND"));
    }

    private List<ShowSeat> getShowSeatsByIds(List<Long> showSeatIds) {
        return showSeatRepository.findAllById(showSeatIds);
    }

    private void validateShowSeatsAvailability(List<ShowSeat> showSeats) throws ShowSeatIsNotAvailable {
        List<ShowSeat> unavailableSeats = showSeats.stream()
                .filter(seat -> !seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))
                .toList();

        if (!unavailableSeats.isEmpty()) {
            throw new ShowSeatIsNotAvailable("SEAT WITH ID: " + unavailableSeats.get(0).getId() + " IS NOT AVAILABLE");
        }
    }

    private double calculateAndBlockSeats(List<ShowSeat> showSeats, double basePrice) {
        return showSeats.stream()
                .peek(seat -> seat.setShowSeatStatus(ShowSeatStatus.BOOKED))
                .mapToDouble(seat -> calculatePrice.calculatePrice(basePrice, seat.getSeat().getSeatType()))
                .sum();
    }

    private Booking createAndSaveBooking(User user, Show show, List<ShowSeat> showSeats, double totalPrice) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setShowSeats(showSeats);
        booking.setAmount(totalPrice);

        showSeatRepository.saveAll(showSeats);
        return booking;
    }
}