package com.epam.jmp.memory.cache.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.awt.Image;
import java.lang.ref.WeakReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.jmp.test.util.TestData;

@RunWith(MockitoJUnitRunner.class)
public class ImageWeakCacheDecoratorTest {

    private static final String IMG_GOSLING = "https://upload.wikimedia.org/wikipedia/commons/1/14/James_Gosling_2008.jpg";
    @Mock private ImageProvider realProvider;
    private ImageProvider imageCacheProvider;
    private Image sampleImage = TestData.getImage();

    @Before
    public void setUp() {
        given(realProvider.loadImage(IMG_GOSLING)).willReturn(sampleImage);
        imageCacheProvider = new ImageCacheDecorator(WeakReference.class, realProvider);
    }

    @Test
    public void shouldReturnExistingImage() {
        Image image = imageCacheProvider.loadImage(IMG_GOSLING);
        assertThat(image, is(sampleImage));
        verify(realProvider).loadImage(IMG_GOSLING);
    }

    @Test
    public void shouldReturnImageWithOneCallToReadImageProvider() throws Exception {
        Image image = imageCacheProvider.loadImage(IMG_GOSLING);
        image = imageCacheProvider.loadImage(IMG_GOSLING);
        assertThat(image, is(sampleImage));
        verify(realProvider).loadImage(IMG_GOSLING);
    }

}
