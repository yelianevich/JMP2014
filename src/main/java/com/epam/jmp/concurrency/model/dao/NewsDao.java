package com.epam.jmp.concurrency.model.dao;

import com.epam.jmp.concurrency.model.News;

public interface NewsDao {

	News mergeNews(News news);

}
