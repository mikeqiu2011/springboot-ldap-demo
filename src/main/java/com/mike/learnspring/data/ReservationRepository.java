package com.mike.learnspring.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;


public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Iterable<Reservation> findReservationByReservationDate(Date date);
}
