package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class FavoritesBookingsAdapter extends RecyclerView.Adapter<FavoritesBookingsAdapter.ViewHolder> {
    private Context context;
    private List<Place> placeList;

    public FavoritesBookingsAdapter(Context context, List<Place> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorites_bookings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = placeList.get(position);
        Log.d("FavoritesAdapter", "Binding place: " + place.getName());
        Log.d("FavoritesAdapter", "Country: " + place.getCountry());
        Log.d("FavoritesAdapter", "Price: " + place.getStartingPrice());
        Log.d("FavoritesAdapter", "Available Flight: " + place.getAvailableFlight());
        Log.d("FavoritesAdapter", "id: " + place.getId());
        Log.d("FavoritesAdapter", "image: " + place.getImage());
        Log.d("FavoritesAdapter", "Available Flight: " + place.getAvailableFlight());

        if (place != null) {

            // Ensure data is correctly displayed
            holder.placeName.setText(place.getName());
            holder.country.setText(place.getCountry());
            holder.startingPrice.setText("Price: " + place.getStartingPrice());
            holder.availableFlight.setText("Available Flight: " + place.getAvailableFlight());

            // Load image without external libraries
            ImageLoader.loadImage(context, holder.placeImage, place.getImage());

            // Handle View Button Click
            holder.viewButton.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("place_id", place.getId());
                intent.putExtra("place_name", place.getName());
                intent.putExtra("country_name", place.getCountry());
                intent.putExtra("price", place.getStartingPrice());
                intent.putExtra("image", place.getImage());
                intent.putExtra("available_flight", place.getAvailableFlight());
                intent.putExtra("description", place.getDescription());
                // Ensure additional images list is never null
                if (place.getAdditionalImages() != null) {
                    intent.putStringArrayListExtra("additional_images", new ArrayList<>(place.getAdditionalImages()));
                } else {
                    intent.putStringArrayListExtra("additional_images", new ArrayList<>()); // Empty list to prevent crash
                }
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeName, country, startingPrice, availableFlight;
        Button viewButton;
        ImageView placeImage;

        ViewHolder(View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.place_name);
            country = itemView.findViewById(R.id.country);
            startingPrice = itemView.findViewById(R.id.starting_price);
            availableFlight = itemView.findViewById(R.id.available_flight);
            placeImage = itemView.findViewById(R.id.place_image);
            viewButton = itemView.findViewById(R.id.view_button);
        }
    }
}
