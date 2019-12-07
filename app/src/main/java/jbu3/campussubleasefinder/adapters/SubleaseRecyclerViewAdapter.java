package jbu3.campussubleasefinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Review;
import jbu3.campussubleasefinder.models.Sublease;
import jbu3.campussubleasefinder.models.User;

public class SubleaseRecyclerViewAdapter extends RecyclerView.Adapter<SubleaseRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Sublease> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public SubleaseRecyclerViewAdapter(Context context, List<Sublease> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.sublease_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Sublease sublease = mData.get(position);
        final User user = SampleData.findUserByID(sublease.sublessorID, true);

        if (SampleData.isConnection(user.id)) {
            holder.connectionImage.setVisibility(View.VISIBLE);
        } else {
            holder.connectionImage.setVisibility(View.GONE);
        }
        holder.nameText.setText(user.name);
        holder.ratingText.setText(Double.toString(user.rating));
        holder.connectionsText.setText(SampleData.findNumConnections(0, sublease.sublessorID).toString());
        holder.detailsText.setText(sublease.details);
        holder.priceText.setText("$" + sublease.price + "/month");
        holder.startText.setText("Start: " + sublease.startDate);
        holder.endText.setText("End: " + sublease.endDate);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;

        ImageView connectionImage;
        TextView nameText;
        TextView ratingText;
        TextView connectionsText;
        TextView detailsText;
        TextView priceText;
        TextView startText;
        TextView endText;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.sublease_row_card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
                }
            });

            connectionImage = itemView.findViewById(R.id.sublease_row_connection_image);
            nameText = itemView.findViewById(R.id.sublease_row_name_text_view);
            ratingText = itemView.findViewById(R.id.sublease_row_rating_text_view);
            connectionsText = itemView.findViewById(R.id.sublease_row_connections_text_view);
            detailsText = itemView.findViewById(R.id.sublease_row_details_text_view);
            priceText = itemView.findViewById(R.id.sublease_row_price_text_view);
            startText = itemView.findViewById(R.id.sublease_row_start_date_text_view);
            endText = itemView.findViewById(R.id.sublease_row_end_date_text_view);
        }
    }

    // convenience method for getting data at click position
    Sublease getItem(int id) {
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