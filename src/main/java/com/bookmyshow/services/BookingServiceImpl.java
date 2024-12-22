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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService{
    private final UserRepository userRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;


    public BookingServiceImpl(UserRepository userRepository, ShowSeatRepository showSeatRepository, ShowRepository showRepository) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException, ShowNotFoundException, ShowSeatIsNotAvailable {
        //  1. Get the user with the given userId.
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("USER WITH THE GIVEN ID: "+ userId +" IS NOT FOUND");
        }

        //2. Get the show with the given showId.
        Optional<Show> optionalShow=showRepository.findById(showId);
        if (optionalShow.isEmpty()){
            throw new ShowNotFoundException("SHOW WITH THE GIVEN ID: "+ showId +" IS NOT FOUND");
        }
       //  3. Get the List of showSeats with the given id's.
        List<ShowSeat> showSeats=showSeatRepository.findAllById(showSeatIds);

        // 4. Check if all the seats are available or not.
        for(ShowSeat showSeat:showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                //5. If not, throw an exception.
                throw new ShowSeatIsNotAvailable("SEAT WITH ID: "+showSeat.getId()+" IS NOT AVAILABLE");
            }
        }

        //6. If yes, Mark the status of all the seats as BLOCKED.
        for(ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            // 7. Save the changes in the DB as well.
            showSeatRepository.save(showSeat);
        }
        Booking booking=new Booking();
        booking.setUser(optionalUser.get());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShow(optionalShow.get());
        booking.setPayments(new ArrayList<>());
        booking.setShowSeats(showSeats);
        booking.setAmount(100.00);
        return null;
    }
}
