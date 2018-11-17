package com.example.jason.jetpackdemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jason.jetpackdemo.ResubscribableMutableLiveData;
import com.example.jason.jetpackdemo.model.PlantData;
import com.example.jason.jetpackdemo.network.JetpackAPI;
import com.example.jason.jetpackdemo.network.PlantListService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class JetpackDemoRepository extends DisposableSingleObserver<PlantData> {

    private static final String QUERY_FIELDS = "Genus";

    @NonNull
    private final MutableLiveData<PlantData> result = new ResubscribableMutableLiveData<>();

    private PlantListService service = JetpackAPI.getClient().create(PlantListService.class);

    public static JetpackDemoRepository newInstance() {
        return new JetpackDemoRepository();
    }

    public LiveData<PlantData> fetchPlantList(int limit) {

        service.fetchPlants(limit, QUERY_FIELDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

        return result;
    }

    @Override
    public void onSuccess(PlantData plantData) {
        result.postValue(plantData);
    }

    @Override
    public void onError(Throwable e) {
        result.postValue(null);
    }
}
