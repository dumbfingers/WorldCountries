package com.yeyaxi.android.worldcountries;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yeyaxi.android.worldcountries.api.ApiClient;
import com.yeyaxi.android.worldcountries.model.Country;
import com.yeyaxi.android.worldcountries.model.Flag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.body)
    TextView body;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.backdrop)
    ImageView backDrop;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_country_detail);
        unbinder = ButterKnife.bind(this);

        setupToolbar();
        Intent args = getIntent();
        ApiClient apiClient = new ApiClient();

        if (args != null) {
            String countryCode = args.getStringExtra(Params.PARAM_COUNTRY_CODE);

            Observable<Country> postObservable = apiClient.getApiInterface().getCountry(countryCode);

            postObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            this::fillView,
                            throwable -> showError()
                    );
        }
    }


    private void setupToolbar() {
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void fillView(Country country) {
        if (country == null) {
            return;
        }

        this.body.setText(country.getCapital());
        this.collapsingToolbar.setTitle(country.getName());
        loadBackdrop(country.getCode());
    }

    private void loadBackdrop(String countryCode) {
        String path = Flag.getUri(countryCode);
        Glide.with(this)
                .asGif()
                .load(path)
                .into(this.backDrop);
    }

    private void showError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
    }
}

