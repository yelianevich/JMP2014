package com.epam.jmp.test.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public final class TestData {

	public static List<String> getNewsXmlStr() {
		List<String> line = new ArrayList<>();
		line.add("<news>" + "<title>title test</title>" + "<short_text>short text</short_text>"
				+ "<full_text>full text</full_text>"
				+ "<tags><tag>tag1</tag><tag>tag2</tag></tags>" + "</news>");
		return line;
	}

	public static Image getImage() {
		return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	}

	private TestData() {
	}
}
