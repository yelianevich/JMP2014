package com.epam.jmp.memory.phantom;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class MortemPhantomReference<T extends Phrase> extends PhantomReference<T> {
    private Phrase phrase;

    public MortemPhantomReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
        this.phrase = referent;
    }

    @Override
    public void clear() {
        phrase.dispose();
        super.clear();
    }

}
