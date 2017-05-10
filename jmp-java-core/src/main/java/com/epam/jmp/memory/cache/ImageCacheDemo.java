package com.epam.jmp.memory.cache;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.memory.cache.service.ImageCacheDecorator;
import com.epam.jmp.memory.cache.service.ImageProvider;
import com.epam.jmp.memory.cache.service.WebImageProvider;

public final class ImageCacheDemo {
    private static final Logger LOG = LogManager.getLogger(ImageCacheDecorator.class);

    public static void main(String[] args) throws InterruptedException {
        // -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -Xmx100m
        LOG.info("Start weak image cache demo");
        LOG.info("--------------------------------");
        Thread.sleep(10000);

        String catchingFire = "http://www.hdwallpapers.in/walls/jennifer_lawrence_in_hunger_games_mockingjay-wide.jpg"; // 3.3 MB
        String lucy = "http://www.hdwallpapers.in/walls/lucy_scarlett_johansson-wide.jpg"; // 1.3 MB
        String hobbit = "http://www.hdwallpapers.in/walls/2014_the_hobbit_the_battle_of_the_five_armies-wide.jpg"; // 2.3 MB
        String street = "http://www.hdwallpapers.in/walls/21_jump_street_movie-HD.jpg"; // 627 KB
        String gosling = "https://upload.wikimedia.org/wikipedia/commons/1/14/James_Gosling_2008.jpg"; // 885 KB
        String petrosyan = "https://upload.wikimedia.org/wikipedia/commons/2/23/Evgeny_Petrosyan.jpg"; // 910 KB
        String tricky = "https://upload.wikimedia.org/wikipedia/commons/d/df/Tricky_%40_INmusic_festival.jpg"; // 110 KB
        String pozner = "https://upload.wikimedia.org/wikipedia/commons/f/f2/Vladimir_Posner_2013_04.jpg"; // 5.4 MB
        String brattPit = "http://www.hdwallpapers.in/walls/brad_pitt_in_fury-wide.jpg"; // 1.1 MB

        ImageProvider imageProvider = new ImageCacheDecorator(SoftReference.class, new WebImageProvider());

        imageProvider.loadImage(catchingFire);
        imageProvider.loadImage(lucy);
        imageProvider.loadImage(hobbit);
        imageProvider.loadImage(street);
        imageProvider.loadImage(pozner);
        imageProvider.loadImage(lucy);
        imageProvider.loadImage(catchingFire);
        imageProvider.loadImage(street);
        imageProvider.loadImage(brattPit);
        imageProvider.loadImage(pozner);
        imageProvider.loadImage(tricky);
        imageProvider.loadImage(brattPit);
        imageProvider.loadImage(petrosyan);
        imageProvider.loadImage(gosling);
        imageProvider.loadImage(pozner);

        LOG.info("--------------------------------");
        LOG.info("Start soft image cache demo");
        Thread.sleep(1000);

        imageProvider = new ImageCacheDecorator(WeakReference.class, new WebImageProvider());

        imageProvider.loadImage(street);
        imageProvider.loadImage(pozner);
        imageProvider.loadImage(brattPit);
        imageProvider.loadImage(pozner);
        imageProvider.loadImage(tricky);
        imageProvider.loadImage(petrosyan);
        imageProvider.loadImage(gosling);
        imageProvider.loadImage(pozner);

        LOG.info("--------------------------------");
        LOG.info("Demo has finished");
    }

}
