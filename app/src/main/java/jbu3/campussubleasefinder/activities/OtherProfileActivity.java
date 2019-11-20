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

public class OtherProfileActivity extends AppCompatActivity implements ConnectionRecyclerViewAdapter.ItemClickListener {
    private ConnectionRecyclerViewAdapter connectionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        RecyclerView connectionsRecyclerView = findViewById(R.id.profile_connections_recycler_view);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        connectionsRecyclerView.setLayoutManager(layoutManager);
        connectionsAdapter = new ConnectionRecyclerViewAdapter(this, SampleData.users);
        connectionsAdapter.setClickListener(this);
        connectionsRecyclerView.setAdapter(connectionsAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        // start user intent
    }
}
