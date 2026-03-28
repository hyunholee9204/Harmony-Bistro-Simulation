package com.example.WebRestaurant.Repository;

import com.example.WebRestaurant.Entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByStatus(String status);
}
