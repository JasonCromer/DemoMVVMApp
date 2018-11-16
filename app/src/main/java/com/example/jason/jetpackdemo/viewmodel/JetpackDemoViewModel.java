package com.example.jason.jetpackdemo.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.jason.jetpackdemo.model.PlantData;
import com.example.jason.jetpackdemo.repository.JetpackDemoRepository;

public class JetpackDemoViewModel extends ViewModel {

    @NonNull
    private final JetpackDemoRepository repository = JetpackDemoRepository.newInstance();

    @NonNull
    private final MutableLiveData<Integer> limitLiveData = new MutableLiveData<>();

    @NonNull
    private final LiveData<PlantData> resultLiveData = Transformations.switchMap(limitLiveData,
            new Function<Integer, LiveData<PlantData>>() {
                @Override
                public LiveData<PlantData> apply(Integer input) {
                    return repository.fetchPlantList(input);
                }
            });

    public void setLimitForQuery(int limit) {
        limitLiveData.setValue(limit);
    }

    @NonNull
    public LiveData<PlantData> getPlantData() {
        return resultLiveData;
    }

}
