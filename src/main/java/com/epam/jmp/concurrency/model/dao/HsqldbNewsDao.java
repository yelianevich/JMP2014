package com.epam.jmp.concurrency.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.jmp.concurrency.model.News;

public class HsqldbNewsDao implements NewsDao {

	private JdbcTemplate jdbc;
	private static final String MERGE_NEWS =
		"MERGE INTO news USING (VALUES ?, ?, ?, ?) rec (id, title, short_text, full_text) " +
		"ON (news.id = rec.id) " +
		"WHEN MATCHED THEN UPDATE SET news.title = rec.title, news.short_text = rec.short_text, news.full_text = rec.full_text " +
		"WHEN NOT MATCHED THEN INSERT (id, title, short_text, full_text) VALUES (DEFAULT, rec.title, rec.short_text, rec.full_text)";
	
	@Override
	public boolean mergeNews(News news) {
		int affected = jdbc.update(MERGE_NEWS, news.getId(), news.getTitle(), news.getShortText(),
				news.getFullText());
		return affected > 0;
	}

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

}
