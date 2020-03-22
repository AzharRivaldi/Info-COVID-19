package com.azhar.coronainfo.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.azhar.coronainfo.R;
import com.azhar.coronainfo.model.CountryModel;
import com.azhar.coronainfo.viewmodel.CountryViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azhar Rivaldi on 20/03/2020.
 */

public class CountryFragment extends Fragment {

    private ProgressDialog mProgressApp;

    public CountryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressApp = new ProgressDialog(getActivity());
        mProgressApp.setTitle("Mohon tunggu");
        mProgressApp.setCancelable(false);
        mProgressApp.setMessage("Sedang menampilkan data...");
        mProgressApp.show();

        PieChart pieChart = view.findViewById(R.id.idnSummaryPie);
        CountryViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(CountryViewModel.class);
        viewModel.setCountryData();
        viewModel.getCountryData().observe(this, new Observer<CountryModel>() {
            @Override
            public void onChanged(CountryModel countryModel) {
                mProgressApp.dismiss();
                List<PieEntry> pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(countryModel.getIdnConfirmed().getValue(), getResources().getString(R.string.confirmed)));
                pieEntries.add(new PieEntry(countryModel.getIdnRecovered().getValue(), getResources().getString(R.string.recovered)));
                pieEntries.add(new PieEntry(countryModel.getIdnDeaths().getValue(), getResources().getString(R.string.deaths)));

                PieDataSet pieDataSet = new PieDataSet(pieEntries, getResources().getString(R.string.from_corona));
                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                pieDataSet.setValueTextColor(Color.WHITE);
                pieDataSet.setValueTextSize(14);

                Description description = new Description();
                description.setText(getResources().getString(R.string.last_update) + " : " + countryModel.getLastUpdate());
                description.setTextColor(Color.BLACK);
                description.setTextSize(12);

                Legend legend = pieChart.getLegend();
                legend.setTextColor(Color.BLACK);
                legend.setTextSize(12);
                legend.setForm(Legend.LegendForm.SQUARE);

                PieData pieData = new PieData(pieDataSet);
                pieChart.setVisibility(View.VISIBLE);
                pieChart.animateXY(2000, 2000);
                pieChart.setDescription(description);
                pieChart.setHoleRadius(60);
                pieChart.setRotationAngle(130);
                pieChart.setHoleColor(Color.TRANSPARENT);
                pieChart.setData(pieData);
            }
        });
    }
}
