package com.epam.jmp.gc;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MemoryEater {
	static final Logger LOG = LogManager.getLogger(MemoryEater.class);

	public static void main(String[] args) {
		List<byte[]> v = new ArrayList<>();
		while (true) {
			byte b[] = new byte[1048576];
			v.add(b);
			Runtime rt = Runtime.getRuntime();

			System.out.println("free memory: " + rt.freeMemory());
			/*
			 * try { Thread.sleep(250); } catch (InterruptedException e) { }
			 */
		}
	}
}
