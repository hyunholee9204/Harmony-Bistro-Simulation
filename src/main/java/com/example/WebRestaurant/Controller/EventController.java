package com.example.WebRestaurant.Controller;

import com.example.WebRestaurant.Entity.EventEntity;
import com.example.WebRestaurant.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping
    public String eventList(Model model) {

        updateStatusInternal();

        List<EventEntity> ongoing = eventRepository.findByStatus("진행중");
        List<EventEntity> upcoming = eventRepository.findByStatus("진행예정");
        List<EventEntity> ended = eventRepository.findByStatus("종료");

        model.addAttribute("ongoing", ongoing);
        model.addAttribute("upcoming", upcoming);
        model.addAttribute("ended", ended);

        return "html/Event";
    }

    @GetMapping("/{id}")
    public String eventDetail(@PathVariable Long id, Model model) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트 없음: " + id));

        model.addAttribute("event", event);

        return "html/event_detail";
    }

    @GetMapping("/update-status")
    public String updateStatus() {
        updateStatusInternal();
        return "redirect:/event";
    }

    private void updateStatusInternal() {
        List<EventEntity> events = eventRepository.findAll();

        for (EventEntity e : events) {

            if (e.getStartDate() == null || e.getEndDate() == null) continue;

            if (LocalDate.now().isBefore(e.getStartDate())) {
                e.setStatus("진행예정");
            } else if (LocalDate.now().isAfter(e.getEndDate())) {
                e.setStatus("종료");
            } else {
                e.setStatus("진행중");
            }
            eventRepository.save(e);
        }
    }
}