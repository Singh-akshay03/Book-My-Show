package com.bookmyshow.Exceptions;

public class ShowSeatNotAvailable extends Exception {
    public ShowSeatNotAvailable(Long id){
        super(id+"Show Seat is not available");
    }
}
