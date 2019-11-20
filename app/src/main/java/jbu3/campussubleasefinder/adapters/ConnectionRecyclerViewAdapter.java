package jbu3.campussubleasefinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.User;

public class ConnectionRecyclerViewAdapter extends RecyclerView.Adapter<ConnectionRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ConnectionRecyclerViewAdapter(Context context, List<User> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.connection_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = mData.get(position);
        holder.nameTextView.setText(user.name);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView nameTextView;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.connection_card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
                }
            });
            nameTextView = itemView.findViewById(R.id.name_text);
        }
    }

    // convenience method for getting data at click position
    User getItem(int id) {
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