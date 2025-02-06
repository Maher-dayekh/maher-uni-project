package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myproject.DetailsActivity;
import com.example.myproject.R;
import com.example.myproject.model.Place;
import com.example.myproject.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class AllPlacesAdapter extends RecyclerView.Adapter<AllPlacesAdapter.ViewHolder> {
    private Context context;
    private List<Place> placesList;

    public AllPlacesAdapter(Context context, List<Place> placesList) {
        this.context = context;
        this.placesList = placesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.place_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = placesList.get(position);

        holder.placeName.setText(place.getName());
        holder.country.setText(place.getCountry());
        holder.startingPrice.setText(place.getStartingPrice());
        holder.availableFlight.setText("Flight: " + place.getAvailableFlight());

        // Load image from URL
        ImageLoader.loadImage(holder.placeImage, place.getImage());

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, DetailsActivity.class);
            i.putExtra("place_name", place.getName());
            i.putExtra("country_name", place.getCountry());
            i.putExtra("price", place.getStartingPrice());
            i.putExtra("image", place.getImage());
            i.putExtra("available_flight", place.getAvailableFlight());
            i.putExtra("description", place.getDescription());
            i.putStringArrayListExtra("additional_images", new ArrayList<>(place.getAdditionalImages()));

            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView placeImage;
        TextView placeName, country, startingPrice, availableFlight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            country = itemView.findViewById(R.id.country);
            startingPrice = itemView.findViewById(R.id.starting_price);
            availableFlight = itemView.findViewById(R.id.available_flight);
        }
    }
}
