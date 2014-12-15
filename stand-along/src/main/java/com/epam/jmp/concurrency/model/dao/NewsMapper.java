package com.epam.jmp.concurrency.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.jmp.concurrency.model.News;

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
