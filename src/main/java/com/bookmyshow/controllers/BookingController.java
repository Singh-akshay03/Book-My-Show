package com.bookmyshow.controllers;

import com.bookmyshow.dtos.BookingResponseDto;
import com.bookmyshow.dtos.CreateBookingRequestDto;
import com.bookmyshow.models.Booking;
import com.bookmyshow.services.IBookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private final IBookingService bookingService;
    public BookingController(IBookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookingResponseDto createBooking(CreateBookingRequestDto createBookingRequest){
        Booking booking= bookingService.createBooking(createBookingRequest.getUserId(), createBookingRequest.getShowSeatIds(), createBookingRequest.getShowId());
        return mapBookingToBookingResponseDto(booking);
    }

    private BookingResponseDto mapBookingToBookingResponseDto(Booking booking){
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setBookingId(booking.getId());
        return bookingResponseDto;
    }
}
