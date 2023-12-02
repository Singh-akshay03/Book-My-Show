package com.bookmyshow.Repositories;

import com.bookmyshow.Models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    public Optional<ShowSeat> findById(Long ShowSeatId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    public List<ShowSeat> findAllByIdIn(List<Long> shoeSeatIds);
}
