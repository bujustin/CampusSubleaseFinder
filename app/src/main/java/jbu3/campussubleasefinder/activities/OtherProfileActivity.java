package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.adapters.ConnectionRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.ReviewsRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;

public class OtherProfileActivity extends AppCompatActivity implements ConnectionRecyclerViewAdapter.ItemClickListener, SubleaseRecyclerViewAdapter.ItemClickListener, ReviewsRecyclerViewAdapter.ItemClickListener {
    private SubleaseRecyclerViewAdapter subleasesAdapter;
    private ReviewsRecyclerViewAdapter reviewsAdapter;
    private ConnectionRecyclerViewAdapter connectionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        RecyclerView subleaseRecyclerView = findViewById(R.id.profile_subleases_recycler_view);
        LinearLayoutManager subleaseLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        subleaseRecyclerView.setLayoutManager(subleaseLayoutManager);
        subleasesAdapter = new SubleaseRecyclerViewAdapter(this, SampleData.subleases);
        subleasesAdapter.setClickListener(this);
        subleaseRecyclerView.setAdapter(subleasesAdapter);

        RecyclerView reviewsReyclerView = findViewById(R.id.profile_reviews_recycler_view);
        LinearLayoutManager reviewsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        reviewsReyclerView.setLayoutManager(reviewsLayoutManager);
        reviewsAdapter = new ReviewsRecyclerViewAdapter(this, SampleData.reviews);
        reviewsAdapter.setClickListener(this);
        reviewsReyclerView.setAdapter(reviewsAdapter);

        RecyclerView connectionsRecyclerView = findViewById(R.id.profile_connections_recycler_view);
        LinearLayoutManager connectionsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        connectionsRecyclerView.setLayoutManager(connectionsLayoutManager);
        connectionsAdapter = new ConnectionRecyclerViewAdapter(this, SampleData.users);
        connectionsAdapter.setClickListener(this);
        connectionsRecyclerView.setAdapter(connectionsAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        // start user intent
    }
}
