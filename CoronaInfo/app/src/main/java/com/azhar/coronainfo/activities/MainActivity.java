package com.azhar.coronainfo.activities;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.azhar.coronainfo.R;
import com.azhar.coronainfo.fragment.RiwayatFragment;
import com.azhar.coronainfo.fragment.CountryFragment;
import com.azhar.coronainfo.fragment.RingkasanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Azhar Rivaldi on 20/03/2020.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    TextView tvToday;
    String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            RingkasanFragment ringkasanFragment = new RingkasanFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.flMain, ringkasanFragment)
                    .commit();
        }

        tvToday = findViewById(R.id.tvDate);
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getToday();

    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.summaryMenu:
                RingkasanFragment ringkasanFragment = new RingkasanFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, ringkasanFragment)
                        .commit();
                return true;

            case R.id.summaryIdnMenu:
                CountryFragment countryFragment = new CountryFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, countryFragment)
                        .commit();
                return true;

            case R.id.historyMenu:
                RiwayatFragment riwayatFragment = new RiwayatFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, riwayatFragment)
                        .commit();
                return true;
        }
        return false;
    }
}
