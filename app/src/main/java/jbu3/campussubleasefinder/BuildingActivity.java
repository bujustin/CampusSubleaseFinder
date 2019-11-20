package jbu3.campussubleasefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BuildingActivity extends AppCompatActivity implements BuildingInfoFragment.OnFragmentInteractionListener, BuildingSubleasesFragment.OnFragmentInteractionListener{

    TextView addressText;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        addressText = findViewById(R.id.building_address);
        bottomNav = findViewById(R.id.building_bottom_nav);

        final Fragment buildingInfo = new BuildingInfoFragment();
        final Fragment buildingSubleases = new BuildingSubleasesFragment();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.add(R.id.building_frame, buildingInfo);
        t.commit();

        bottomNav.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                    switch (item.getItemId()) {
                        case R.id.action_info:
                            t.replace(R.id.building_frame, buildingInfo);
                            t.commit();
                            break;
                        case R.id.action_subleases:
                            t.replace(R.id.building_frame, buildingSubleases);
                            t.commit();
                            break;
                    }
                    return false;
                }
            });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
