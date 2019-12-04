package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.adapters.BuildingRecyclerViewAdapter;
import jbu3.campussubleasefinder.R;

public class MainActivity extends AppCompatActivity implements BuildingRecyclerViewAdapter.ItemClickListener {

    private BuildingRecyclerViewAdapter buildingsAdapter;
    private FloatingActionButton postSubleaseFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SampleData.filteredBuildings = SampleData.buildings;

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_account_circle_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView buildingsRecyclerView = findViewById(R.id.main_building_recycler_view);
        buildingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        buildingsAdapter = new BuildingRecyclerViewAdapter(this, SampleData.buildings);
        buildingsAdapter.setClickListener(this);
        buildingsRecyclerView.setAdapter(buildingsAdapter);

        postSubleaseFab = findViewById(R.id.add_sublease_fab);
        postSubleaseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postSubleaseIntent = new Intent(getApplicationContext(), PostSubleaseActivity.class);
                startActivity(postSubleaseIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent showProfileIntent = new Intent(this, ProfileActivity.class);
                showProfileIntent.putExtra("PROFILE_CAN_EDIT", true);
                startActivity(showProfileIntent);
                return true;
            case R.id.filters:
                Intent showFiltersIntent = new Intent(this, FilterActivity.class);
                startActivity(showFiltersIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent showBuildingIntent = new Intent(this, BuildingActivity.class);
        showBuildingIntent.putExtra(BuildingActivity.ARG_BUILDING_ID, position);
        startActivity(showBuildingIntent);
    }
}
