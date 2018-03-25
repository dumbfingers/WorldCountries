package com.yeyaxi.android.worldcountries.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yeyaxi.android.worldcountries.adapter.CountryAdapter;
import com.yeyaxi.android.worldcountries.Params;
import com.yeyaxi.android.worldcountries.R;
import com.yeyaxi.android.worldcountries.activity.DetailActivity;
import com.yeyaxi.android.worldcountries.api.ApiClient;
import com.yeyaxi.android.worldcountries.model.Country;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private ApiClient apiClient;
    private CountryAdapter adapter;
    private List<Country> countries;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        this.countries = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.apiClient = new ApiClient();
        this.adapter = new CountryAdapter();
        this.adapter.setDelegate(country -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(Params.PARAM_COUNTRY_CODE, country.getCode());
            getContext().startActivity(intent);
        });
        this.adapter.setDataSource(this.countries);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(llm);
        this.recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Observable<List<Country>> countriesObservable = this.apiClient.getApiInterface().getAllCountries();
        countriesObservable
                .flatMap(Observable::fromIterable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        country -> {
                            this.countries.add(country);
                            this.adapter.notifyItemInserted(this.countries.size() - 1);
                        },
                        throwable -> showError(),
                        () -> {});
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void showError() {
        if (!isResumed()) {
            return;
        }
        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_LONG).show();
    }
}
