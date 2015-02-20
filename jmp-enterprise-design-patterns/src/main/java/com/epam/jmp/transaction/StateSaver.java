package com.epam.jmp.transaction;

public interface StateSaver <T extends CloneableData<T>> {

	void saveState(T state);

	T restoreState();
}
