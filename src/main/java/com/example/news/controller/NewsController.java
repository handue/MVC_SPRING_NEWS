package com.example.news.controller;

import com.example.news.domain.News;
import com.example.news.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String list(Model model) {
        List<News> newsList = newsService.findAll();
        model.addAttribute("newslist", newsList);
        return "newsList";
    }

    @GetMapping("/{aid}")
    public String view(@PathVariable int aid, Model model) {
        News news = newsService.findById(aid);
        model.addAttribute("news", news);
        return "newsView";
    }

    @PostMapping
    public String add(@ModelAttribute News news, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // 파일 처리 로직
            String fileName = file.getOriginalFilename(); // 실제 파일 이름
            String filePath = "C:\\Users\\hy4hj\\Desktop\\springImage\\" + fileName;
            try {
                file.transferTo(new File(filePath)); // 파일 저장
                news.setImg(fileName); // 저장된 파일 이름을 News 객체에 설정
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        newsService.save(news);
        return "redirect:/news";
    }

    @PostMapping("/delete/{aid}")
    public String delete(@PathVariable int aid) {
        newsService.deleteById(aid);
        return "redirect:/news";
    }
}
