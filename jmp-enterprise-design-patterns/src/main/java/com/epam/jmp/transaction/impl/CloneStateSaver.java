package com.epam.jmp.transaction.impl;

import com.epam.jmp.transaction.CloneableData;
import com.epam.jmp.transaction.StateSaver;

public class CloneStateSaver<T extends CloneableData<T>> implements StateSaver<T> {

    private T state;

    @Override
    public void saveState(T state) {
        this.state = state.cloneData();
    }

    @Override
    public T restoreState() {
        return state;
    }

}
