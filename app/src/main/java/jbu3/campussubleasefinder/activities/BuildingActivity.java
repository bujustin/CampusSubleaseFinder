package jbu3.campussubleasefinder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.adapters.BuildingRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;
import jbu3.campussubleasefinder.fragments.BuildingInfoFragment;
import jbu3.campussubleasefinder.fragments.BuildingSubleasesFragment;
import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.Sublease;

import static jbu3.campussubleasefinder.SampleData.buildings;

public class BuildingActivity extends AppCompatActivity implements BuildingInfoFragment.OnFragmentInteractionListener, BuildingSubleasesFragment.OnFragmentInteractionListener {
    public static final String ARG_BUILDING_ID = "buildingID";

    private Building building;

    private ImageView buildingImage;
    private TextView addressText;
    private BottomNavigationView bottomNav;

    Fragment buildingInfo;
    Fragment buildingSubleases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int buildingID = getIntent().getIntExtra(ARG_BUILDING_ID, 0);
        building = SampleData.findBuildingByID(buildingID);

        buildingImage = findViewById(R.id.building_header_image);
        addressText = findViewById(R.id.building_address);
        bottomNav = findViewById(R.id.building_bottom_nav);

        buildingInfo = BuildingInfoFragment.newInstance(buildingID);
        buildingSubleases = BuildingSubleasesFragment.newInstance(buildingID);

        Picasso.get()
                .load(building.imageURL)
                .transform(new BlurTransform(getApplicationContext()))
                .fit()
                .centerCrop()
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorPrimary)
                .into(buildingImage);

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
                            item.setChecked(true);
                            break;
                        case R.id.action_subleases:
                            t.replace(R.id.building_frame, buildingSubleases);
                            t.commit();
                            item.setChecked(true);
                            break;
                    }
                    return false;
                }
            });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private final class BlurTransform implements Transformation {

        WeakReference<Context> context;

        public BlurTransform(Context context) {
            super();
            this.context = new WeakReference<>(context);
        }

        @Override
        public Bitmap transform(Bitmap bitmap) {
            RenderScript rs = RenderScript.create(context.get());
            Bitmap source = bitmap.copy(Bitmap.Config.ARGB_8888, true);

            if (source == null) {
                return null;
            }

            Allocation input = Allocation.createFromBitmap(rs, source,
                    Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(1);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(source);

            // also darken the image
            Paint paint = new Paint();
            ColorFilter filter = new LightingColorFilter(0xFF444444, 0x22222222);
            paint.setColorFilter(filter);
            Canvas canvas = new Canvas(source);
            canvas.drawBitmap(source, 0, 0, paint);

            bitmap.recycle();

            return source;
        }

        @Override
        public String key() {
            return "blur";
        }

    }
}
