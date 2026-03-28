package com.example.WebRestaurant.Controller;

import com.example.WebRestaurant.Entity.ReservationEntity;
import com.example.WebRestaurant.Repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationRepository reservationRepository;

    @GetMapping("/reservation")
    public String reservationPage(Model model) {

        List<LocalDate> fullDates = getFullDates();
        model.addAttribute("fullDates", fullDates);
        model.addAttribute("reservations", reservationRepository.findAll());
        return "html/Reservation";
    }

    private List<LocalDate> getFullDates() {

        Map<LocalDate, Integer> countMap = new HashMap<>();

        reservationRepository.findAll().forEach(r -> {
            countMap.put(r.getReservationDate(),
                    countMap.getOrDefault(r.getReservationDate(), 0) + 1);
        });

        return countMap.entrySet().stream()
                .filter(e -> e.getValue() >= 10)
                .map(Map.Entry::getKey)
                .toList();
    }

    @PostMapping("/reservation/save")
    @ResponseBody
    public String saveReservation(@RequestBody ReservationEntity reservation) {

        reservationRepository.save(reservation);

        return "ok";
    }
}
