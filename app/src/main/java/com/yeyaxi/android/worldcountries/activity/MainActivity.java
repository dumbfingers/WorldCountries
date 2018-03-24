package com.yeyaxi.android.worldcountries.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yeyaxi.android.worldcountries.MainFragment;
import com.yeyaxi.android.worldcountries.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit();
    }
}
