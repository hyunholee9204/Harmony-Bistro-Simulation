package com.example.WebRestaurant.Controller;

import com.example.WebRestaurant.Entity.NoticeEntity;
import com.example.WebRestaurant.Repository.NoticeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoticeController {

    private final NoticeRepository noticeRepository;

    public NoticeController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @GetMapping("/notice")
    public String noticeList(Model model) {
        model.addAttribute("list", noticeRepository.findAll());
        return "html/Notice";
    }

    @GetMapping("/notice/{id}")
    public String noticeDetail(@PathVariable Long id, Model model) {
        NoticeEntity notice = noticeRepository.findById(id).orElseThrow();
        model.addAttribute("notice", notice);
        return "html/Notice_detail";
    }
}
