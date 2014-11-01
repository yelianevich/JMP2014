package com.epam.jmp.memory.cache.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Image;
import java.lang.ref.SoftReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.jmp.test.util.TestData;

@RunWith(MockitoJUnitRunner.class)
public class ImageSoftDecoratorTest {

	private static final String IMG_GOSLING = "https://upload.wikimedia.org/wikipedia/commons/1/14/James_Gosling_2008.jpg";
	@Mock private ImageProvider realProvider;
	private ImageProvider imageCacheProvider;
	private Image sampleImage = TestData.getImage();

	@Before
	public void setUp() {
		when(realProvider.loadImage(IMG_GOSLING)).thenReturn(sampleImage);

		@SuppressWarnings("unchecked")
		Class<? extends SoftReference<Image>> refType = (Class<? extends SoftReference<Image>>) SoftReference.class;
		imageCacheProvider = new ImageCacheDecorator(refType, realProvider);
	}

	@Test
	public void returnExistingImage() {
		Image image = imageCacheProvider.loadImage(IMG_GOSLING);
		assertThat(image, is(sampleImage));
		verify(realProvider).loadImage(IMG_GOSLING);
	}

	@Test
	public void returnImageOnSecondCall() throws Exception {
		Image image = imageCacheProvider.loadImage(IMG_GOSLING);
		image = imageCacheProvider.loadImage(IMG_GOSLING);
		assertThat(image, is(sampleImage));
		verify(realProvider).loadImage(IMG_GOSLING);
	}

}
