package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
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

        if (place != null) {
            holder.placeName.setText(place.getName());
            holder.country.setText(place.getCountry());
            holder.price.setText(place.getStartingPrice());

            holder.viewButton.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("place_id", place.getId());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeName, country, price;
        Button viewButton;
        ImageView placeImage;

        ViewHolder(View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.place_name);
            country = itemView.findViewById(R.id.country);
            price = itemView.findViewById(R.id.starting_price);
            viewButton = itemView.findViewById(R.id.view_button);
            placeImage = itemView.findViewById(R.id.place_image);
        }
    }
}
