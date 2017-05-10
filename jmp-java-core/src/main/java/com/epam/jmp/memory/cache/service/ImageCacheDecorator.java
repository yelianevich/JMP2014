package com.epam.jmp.memory.cache.service;

import java.awt.Image;
import java.lang.ref.Reference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageCacheDecorator implements ImageProvider {
    private static final Logger LOG = LogManager.getLogger(ImageCacheDecorator.class);

    private ImageProvider imageProvider;

    private Map<String, Reference<Image>> cache = new HashMap<>();
    private Class<?> refType;

    public ImageCacheDecorator(Class<?> refType, ImageProvider imageProvider) {
        this.imageProvider = imageProvider;
        this.refType = refType;
    }

    @Override
    public Image loadImage(String url) {
        long startTime = System.currentTimeMillis();

        Reference<Image> value = cache.get(url);
        Image image = null;
        if (value == null) {
            LOG.info("Cache miss. Load: " + url);
            image = imageProvider.loadImage(url);
            Reference<Image> cacheRef = createCacheEntry(image);
            cache.put(url, cacheRef);
        } else {
            LOG.info("Cache hit. Get: " + url);
            image = value.get();
            if (image == null) {
                LOG.info("Image was GC'ed. Get again from provider");
                image = imageProvider.loadImage(url);
                Reference<Image> cacheRef = createCacheEntry(image);
                cache.put(url, cacheRef);
            }
        }
        long timeElapsed = System.currentTimeMillis() - startTime;
        LOG.info("Image loaded in " + timeElapsed + " ms");
        return image;
    }

    private Reference<Image> createCacheEntry(Image image) {
        Reference<Image> cacheRef = null;
        try {
            @SuppressWarnings("unchecked")
            Constructor<Reference<Image>> constr = (Constructor<Reference<Image>>) refType.getConstructor(Object.class);
            cacheRef = constr.newInstance(image);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            LOG.error(e);
            throw new IllegalArgumentException("Reference subclass Class passed to the constructor is wrong");
        }

        return cacheRef;
    }
}
