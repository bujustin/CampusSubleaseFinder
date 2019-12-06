package jbu3.campussubleasefinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.Review;

public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Review> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ReviewsRecyclerViewAdapter(Context context, List<Review> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.review_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Review review = mData.get(position);
        holder.nameText.setText(SampleData.findUserByID(review.userID, false).name);
        holder.ratingsBar.setRating((float)review.rating);
        holder.detailsText.setText(review.text);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;

        TextView nameText;
        RatingBar ratingsBar;
        TextView detailsText;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.review_row_card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
                }
            });

            nameText = itemView.findViewById(R.id.review_row_name);
            ratingsBar = itemView.findViewById(R.id.review_row_rating);
            detailsText = itemView.findViewById(R.id.review_row_details);
        }
    }

    // convenience method for getting data at click position
    Review getItem(int id) {
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