package com.epam.jmp.memory.cache.service;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.awt.Image;

import org.junit.Test;

public class WebImageProviderTest {

	private static final String TRICKY = "https://upload.wikimedia.org/wikipedia/commons/d/df/Tricky_%40_INmusic_festival.jpg";
	private ImageProvider provider = new WebImageProvider();

	@Test
	public void shouldReturnExistingImage() {
		Image image = provider.loadImage(TRICKY);
		assertThat(image, notNullValue());
		assertThat(image, isA(Image.class));
	}

}
