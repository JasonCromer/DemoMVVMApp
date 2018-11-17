package com.example.jason.jetpackdemo;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

public class ResubscribableMutableLiveData<T> extends MutableLiveData<T> {

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        removeObserver(observer);
        super.observe(owner, observer);
    }
}
