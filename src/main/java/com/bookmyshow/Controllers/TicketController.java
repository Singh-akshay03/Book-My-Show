package com.bookmyshow.Controllers;

import com.bookmyshow.Models.Ticket;
import com.bookmyshow.Services.TicketService;
import com.bookmyshow.dto.BookTicketRequest;
import com.bookmyshow.dto.BookTicketResponse;
import com.bookmyshow.dto.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    public BookTicketResponse bookTicket(BookTicketRequest bookTicketRequest){
        try{
            Ticket ticket=ticketService.bookTicket(bookTicketRequest.getUserId(),
                    bookTicketRequest.getShowId(),
                    bookTicketRequest.getSeatId());
            BookTicketResponse bookTicketResponse=new BookTicketResponse();
            bookTicketResponse.setTicket(ticket);
            bookTicketResponse.setResponseStatus(ResponseStatus.SUCCESS);
            return bookTicketResponse;
        }catch (Exception e){
            BookTicketResponse bookTicketResponse=new BookTicketResponse();
            bookTicketResponse.setResponseStatus(ResponseStatus.FAILURE);
            return bookTicketResponse;
        }
    }
}
