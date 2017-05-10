package com.epam.jmp.concurrency.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.epam.jmp.concurrency.model.News;

@Component("newsMapper")
public class NewsMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();
        news.setId(rs.getInt("id"));
        news.setTitle(rs.getString("title"));
        news.setShortText(rs.getString("short_text"));
        news.setFullText(rs.getString("full_text"));
        return news;
    }

}
