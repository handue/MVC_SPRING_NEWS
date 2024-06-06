package com.example.news.service;

import com.example.news.domain.News;
import com.example.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public News findById(int aid) {
        return newsRepository.findById(aid);
    }

    public void save(News news) {
        newsRepository.save(news);
    }

    public void deleteById(int aid) {
        newsRepository.deleteById(aid);
    }
}
