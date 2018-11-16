package com.example.jason.jetpackdemo;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

class LiveDataUtils {

    static <T> void reObserve(@NonNull LiveData<T> liveData, @NonNull LifecycleOwner lifecycleOwner,
                              @NonNull Observer<T> observer) {
        liveData.removeObserver(observer);
        liveData.observe(lifecycleOwner, observer);
    }
}
