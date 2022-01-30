package com.mike.learnspring.util;

import com.mike.learnspring.data.Reservation;
import com.mike.learnspring.data.ReservationRepository;
import com.mike.learnspring.data.Room;
import com.mike.learnspring.data.RoomRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public AppStartupEvent(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);

        Iterable<Reservation> reservations = null;
        try {
            reservations = this.reservationRepository
                    .findReservationByReservationDate(
                            new SimpleDateFormat("dd/MM/yyyy")
                                    .parse("01/01/2022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservations.forEach(System.out::println);
    }
}
