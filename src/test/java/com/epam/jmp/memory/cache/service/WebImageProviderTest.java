package com.epam.jmp.memory.cache.service;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.awt.Image;

import org.junit.Test;

public class WebImageProviderTest {

	private static final String IMG_GOSLING = "https://upload.wikimedia.org/wikipedia/commons/1/14/James_Gosling_2008.jpg";
	private ImageProvider provider = new WebImageProvider();

	@Test
	public void shouldReturnExistingImage() {
		Image image = provider.loadImage(IMG_GOSLING);
		assertThat(image, notNullValue());
	}

}
