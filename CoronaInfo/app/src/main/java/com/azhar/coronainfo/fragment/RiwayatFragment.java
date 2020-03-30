package com.azhar.coronainfo.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.coronainfo.R;
import com.azhar.coronainfo.adapter.HistoryListAdapter;
import com.azhar.coronainfo.model.RiwayatModel;
import com.azhar.coronainfo.viewmodel.RiwayatViewModel;

import java.util.ArrayList;

/**
 * Created by Azhar Rivaldi on 20/03/2020.
 */

public class RiwayatFragment extends Fragment {

    private HistoryListAdapter adapter;
    private ProgressDialog mProgressApp;

    public RiwayatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressApp = new ProgressDialog(getActivity());
        mProgressApp.setTitle("Mohon tunggu");
        mProgressApp.setCancelable(false);
        mProgressApp.setMessage("Sedang menampilkan data...");

        RecyclerView recyclerView = view.findViewById(R.id.listRecycler);
        adapter = new HistoryListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        loadListData();
    }

    private void loadListData() {
        RiwayatViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(RiwayatViewModel.class);
        viewModel.setTodayData();
        mProgressApp.show();
        viewModel.getTodayListData().observe(this, new Observer<ArrayList<RiwayatModel>>() {
            @Override
            public void onChanged(ArrayList<RiwayatModel> riwayatModels) {
                adapter.setRiwayatModels(riwayatModels);
                mProgressApp.dismiss();
            }
        });
    }
}
