package com.epam.jmp.concurrency.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.epam.jmp.concurrency.model.News;

@Repository
public class HsqldbNewsDao implements NewsDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private RowMapper<News> newsMapper;

	private static final String MERGE_NEWS =
		"MERGE INTO news USING (VALUES ?, ?, ?, ?) rec (id, title, short_text, full_text) "
		+ "ON (news.id = rec.id) "
		+ "WHEN MATCHED THEN UPDATE SET news.title = rec.title, news.short_text = rec.short_text, news.full_text = rec.full_text "
		+ "WHEN NOT MATCHED THEN INSERT (id, title, short_text, full_text) VALUES (DEFAULT, rec.title, rec.short_text, rec.full_text)";
	private static final String SELECT_NEWS = "SELECT id, title, short_text, full_text FROM news";
	private static final String SELECT_NEWS_BY_ID = "SELECT id, title, short_text, full_text FROM news WHERE id = ?";

	@Override
	public boolean mergeNews(News news) {
		int affected = jdbc.update(MERGE_NEWS, news.getId(), news.getTitle(), news.getShortText(),
				news.getFullText());
		return affected > 0;
	}

	@Override
	public News getNewsById(int id) {
		return jdbc.queryForObject(SELECT_NEWS_BY_ID, new Object[] {id}, newsMapper);
	}

	@Override
	public List<News> getNews() {
		return jdbc.query(SELECT_NEWS, newsMapper);
	}

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public void setNewsMapper(RowMapper<News> newsMapper) {
		this.newsMapper = newsMapper;
	}

}
