package com.epam.jmp.memory.cache.service;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.awt.Image;
import java.lang.ref.WeakReference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImageWeakCacheDecoratorTest {

	private static final String IMG_GOSLING = "https://upload.wikimedia.org/wikipedia/commons/1/14/James_Gosling_2008.jpg";
	private ImageProvider imageProvider;

	@Before
	public void setUp() {
		WebImageProvider webImageProvider = new WebImageProvider();
		
		@SuppressWarnings("unchecked")
		Class<? extends WeakReference<Image>> refType = (Class<? extends WeakReference<Image>>) WeakReference.class;
		imageProvider = new ImageCacheDecorator(refType, webImageProvider);
	}

	@After
	public void tearDown() {
		imageProvider = null;
	}

	@Test
	public void returnExistingImage() {
		Image image = imageProvider.loadImage(IMG_GOSLING);
		assertThat(image, notNullValue());
	}

	@Test
	public void returnImageOnSecondCall() throws Exception {
		Image image = imageProvider.loadImage(IMG_GOSLING);
		image = imageProvider.loadImage(IMG_GOSLING);
		assertThat(image, notNullValue());
	}

}
