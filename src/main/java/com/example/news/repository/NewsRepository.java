package com.example.news.repository;

import com.example.news.domain.News;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsRepository {

    private final JdbcTemplate jdbcTemplate;

    public NewsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<News> findAll() {
        String sql = "SELECT aid, title, img, date, content FROM news";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(News.class));
    }

    public News findById(int aid) {
        String sql = "SELECT aid, title, img, date, content FROM news WHERE aid = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(News.class), aid);
    }

    public void save(News news) {
        String sql = "INSERT INTO news (title, img, date, content) VALUES (?, ?, CURRENT_TIMESTAMP(), ?)";
        jdbcTemplate.update(sql, news.getTitle(), news.getImg(), news.getContent());
    }

    public void deleteById(int aid) {
        String sql = "DELETE FROM news WHERE aid = ?";
        jdbcTemplate.update(sql, aid);
    }
}
