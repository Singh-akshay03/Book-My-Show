package com.bookmyshow.services;

import com.bookmyshow.exceptions.ShowNotFoundException;
import com.bookmyshow.exceptions.ShowSeatIsNotAvailable;
import com.bookmyshow.exceptions.UserNotFoundException;
import com.bookmyshow.models.Booking;

import java.util.List;

public interface IBookingService {
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException, ShowNotFoundException, ShowSeatIsNotAvailable;
}
