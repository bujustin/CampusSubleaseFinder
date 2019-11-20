package jbu3.campussubleasefinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Building;

public class BuildingRecyclerViewAdapter extends RecyclerView.Adapter<BuildingRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Building> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public BuildingRecyclerViewAdapter(Context context, List<Building> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.building_list_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Building building = mData.get(position);
        holder.addressText.setText(building.address);
        holder.numSubleasesText.setText(building.numSubleases + " Subleases");
        holder.numConnectionsText.setText(building.numConnections + " Connections");
        holder.ratingText.setText(Double.toString(building.rating));
//        Picasso.get()
//                .load(building.imageURL)
//                .resize(80, 80)
//                .centerCrop()
//                .placeholder(R.drawable.ic_home_black_48dp)
//                .error(R.drawable.ic_home_black_48dp)
//                .into(holder.buildingImage);

        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:40.1164,-88.2434?q="+building.address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mContext.startActivity(mapIntent);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        ImageView buildingImage;
        TextView addressText;
        TextView numSubleasesText;
        TextView numConnectionsText;
        TextView ratingText;
        ImageButton mapButton;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.building_row_card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
                }
            });
            buildingImage = itemView.findViewById(R.id.building_row_image);
            addressText = itemView.findViewById(R.id.building_row_address_text);
            numSubleasesText = itemView.findViewById(R.id.building_row_num_subleases_text);
            numConnectionsText = itemView.findViewById(R.id.building_row_num_connections_text);
            ratingText = itemView.findViewById(R.id.building_row_rating_text);
            mapButton = itemView.findViewById(R.id.building_row_map_button);
        }
    }

    // convenience method for getting data at click position
    Building getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}