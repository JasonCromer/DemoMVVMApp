package com.example.jason.jetpackdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason.jetpackdemo.model.Plant;
import com.example.jason.jetpackdemo.model.PlantData;
import com.example.jason.jetpackdemo.viewmodel.JetpackDemoViewModel;

import java.util.List;

public class JetpackDemoFragment extends Fragment {

    private static final int NUM_PLANTS_TO_QUERY = 3;

    private TextView plantLabel;

    private final Observer<PlantData> plantObserver = new Observer<PlantData>() {
        @Override
        public void onChanged(@Nullable PlantData plants) {
            if (plants != null && plants.plantList != null) {
                setPlantLabel(plants.plantList);
            }
        }
    };

    public static JetpackDemoFragment newInstance() {
        return new JetpackDemoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jetpack_demo, container, false);
        plantLabel = view.findViewById(R.id.plants_label);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JetpackDemoViewModel viewModel = ViewModelProviders.of(this).get(JetpackDemoViewModel.class);
        viewModel.getPlantData().observe(this, plantObserver);

        viewModel.setLimitForQuery(NUM_PLANTS_TO_QUERY);
    }

    private void setPlantLabel(@NonNull List<Plant> plants) {
        StringBuilder result = new StringBuilder("Plants: ");

        for (Plant plant : plants) {
            result.append(plant.genus).append(", ");
        }

        // remove extra comma and space
        plantLabel.setText(result.substring(0, result.length() - 2));
    }
}
