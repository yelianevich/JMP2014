package com.epam.jmp.memory.phantom;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PhantomDemo {
    private static final Logger LOG = LogManager.getLogger(PhantomDemo.class);

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Phrase> queue = new ReferenceQueue<>();
        List<Reference<Phrase>> refs = new ArrayList<>();


        Thread thread = new Thread(() -> {
            LOG.info("finalizer started");
            while (true) {
                try {
                    Reference<? extends Phrase> r = queue.remove();
                    r.clear();
                    refs.remove(r);
                    LOG.info("Object cleared");
                } catch (InterruptedException e) {
                    LOG.error("Interrupted", e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        long i = 0;
        while (true) {
            Phrase str = new Phrase("Heavy object asdasdasdasda " + i);
            MortemPhantomReference<Phrase> ref = new MortemPhantomReference<Phrase>(str, queue);
            str = null;
            refs.add(ref);
            ++i;
            Thread.sleep(1000);
        }

    }

}
