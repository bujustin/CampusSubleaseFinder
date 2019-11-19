package jbu3.campussubleasefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BuildingRecyclerViewAdapter.ItemClickListener {

    private ArrayList<Building> buildings = new ArrayList<Building>() {{
        add(new Building("123 Example St.", 4, 2, 4.5));
    }};

    private BuildingRecyclerViewAdapter buildingsAdapter;
    private FloatingActionButton postSubleaseFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_account_circle_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView buildingsRecyclerView = findViewById(R.id.main_building_recycler_view);
        buildingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        buildingsAdapter = new BuildingRecyclerViewAdapter(this, buildings);
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
                startActivity(showProfileIntent);
                return true;
            case R.id.filters:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
