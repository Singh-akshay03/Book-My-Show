package com.bookmyshow.Services;

import com.bookmyshow.Exceptions.ShowSeatNotAvailable;
import com.bookmyshow.Models.SeatStatus;
import com.bookmyshow.Models.ShowSeat;
import com.bookmyshow.Models.Ticket;
import com.bookmyshow.Models.User;
import com.bookmyshow.Repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    private ShowSeatRepository showSeatRepository;

    @Autowired
    public TicketService(ShowSeatRepository showSeatRepository){
        this.showSeatRepository=showSeatRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(long userId, long showId, List<Long> showSeatIds) throws ShowSeatNotAvailable {
        List<ShowSeat> showSeats=showSeatRepository.findAllByIdIn(showSeatIds);

        //CHECK ALL THE REQUESTED SHOW SEATS ARE AVAILABLE OR NOT
        for(ShowSeat showSeat:showSeats){
            if(showSeat.getState()!= SeatStatus.AVAILABLE){
                throw new ShowSeatNotAvailable(showSeat.getId());
            }
        }

        //BLOCK THE SHOW SEATS IF THEY ARE AVAILABLE
        for (ShowSeat showSeat: showSeats) {
            showSeat.setState(SeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        //TODO--> PAYMENT SHOULD BE DONE

        //CHANGE THE STATUS OF ALL THE SHOW SEATS TO BOOKED
        for (ShowSeat showSeat: showSeats) {
            showSeat.setState(SeatStatus.BOOKED);
            showSeatRepository.save(showSeat);
        }

        //GENERATE TICKET
        Ticket ticket=new Ticket();
        //ticket.setBookedBy(new User());
        //TODO--> set all the fields then return the ticket
        return ticket;
    }
}
