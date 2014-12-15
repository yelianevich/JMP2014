package com.epam.jmp.memory.cache.service;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebImageProvider implements ImageProvider {
	private static final Logger LOG = LogManager.getLogger(WebImageProvider.class);

	@Override
	public Image loadImage(String id) {
		Image image = null;
		try {
			URL url = new URL(id);
			image = ImageIO.read(url);
		} catch (IOException e) {
			LOG.error("Image could not be loaded from: " + id);
		}
		return image;
	}

}
