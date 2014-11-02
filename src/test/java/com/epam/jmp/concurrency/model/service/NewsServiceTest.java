package com.epam.jmp.concurrency.model.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.jmp.concurrency.model.News;
import com.epam.jmp.concurrency.model.dao.NewsDao;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {

	private NewsService newsService;
	@Mock
	private NewsDao newsDao;

	@Before
	public void setUp() throws Exception {
		given(newsDao.mergeNews(any(News.class))).willReturn(true);

		newsService = new LocalNewsService(newsDao);
	}

	@Test
	public void test() {
		boolean upserted = newsService.upsertNews(new News());
		assertThat(upserted, is(true));
		verify(newsDao).mergeNews(any(News.class));
	}

}
