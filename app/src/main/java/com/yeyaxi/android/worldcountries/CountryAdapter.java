package com.yeyaxi.android.worldcountries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yeyaxi.android.worldcountries.model.Country;
import com.yeyaxi.android.worldcountries.model.Flag;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> dataSource;
    private OnCountryClick delegate;

    public interface OnCountryClick {
        void onCountryClick(Country country);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if (this.dataSource == null) {
            return;
        }
        Country country = this.dataSource.get(position);
        if (country == null) {
            return;
        }
        holder.title.setText(country.getName());

        holder.itemView.setOnClickListener(view -> {
            if (this.delegate != null) {
                this.delegate.onCountryClick(country);
            }
        });

        String uri = Flag.getUri(country.getCode());
        Glide.with(holder.itemView.getContext()).asGif().load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.dataSource == null ? 0 : this.dataSource.size();
    }

    public void setDelegate(OnCountryClick delegate) {
        this.delegate = delegate;
    }

    public void setDataSource(List<Country> dataSource) {
        this.dataSource = dataSource;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;

        public CountryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
