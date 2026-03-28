package com.example.WebRestaurant.Repository;

import com.example.WebRestaurant.Entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByStoreAndReservationDate(String store, LocalDate date);

    List<ReservationEntity> findByStoreAndReservationDateAndReservationTime(
            String store, LocalDate date, LocalTime time
    );
}