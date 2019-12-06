package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.media.Rating;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.models.FilterData;

public class FilterActivity extends AppCompatActivity {
    EditText addressText;
    CheckBox priceCheck400;
    CheckBox priceCheck400_800;
    CheckBox priceCheck800;
    RadioGroup numBedroomsRadio;
    RadioGroup numBathroomRadio;
    Switch parkingSwitch;
    Switch petSwitch;
    RatingBar ratingBar;
    Button filterButton;

    TextView startDateText;
    TextView endDateText;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    Date startDate;
    Date endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        startDateText = findViewById(R.id.filter_start_date_text);
        endDateText = findViewById(R.id.filter_end_date_text);

        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        startDate = calendar.getTime();
                        startDateText.setText((month + 1) + "/" + day + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, day);
                    endDate = calendar.getTime();
                    endDateText.setText((month + 1) + "/" + day + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        addressText = findViewById(R.id.filter_address_text);
        priceCheck400 = findViewById(R.id.filter_price_400);
        priceCheck400_800 = findViewById(R.id.filter_price_400_800);
        priceCheck800 = findViewById(R.id.filter_price_800);

        numBedroomsRadio = findViewById(R.id.filter_bedrooms_radio);
        numBathroomRadio = findViewById(R.id.filter_bathrooms_radio);

        parkingSwitch = findViewById(R.id.filter_parking_switch);
        petSwitch = findViewById(R.id.filter_pet_switch);
        ratingBar = findViewById(R.id.filter_rating);

        filterButton = findViewById(R.id.filter_button);

        loadFilters();

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int minPrice = -1;
                int maxPrice = -1;
                if (priceCheck400.isChecked()) {
                    minPrice = 0;
                    maxPrice = 400;
                }
                if (priceCheck400_800.isChecked()) {
                    if (minPrice < 0) {
                        minPrice = 400;
                    }
                    maxPrice = 800;
                }
                if (priceCheck800.isChecked()) {
                    if (minPrice < 0) {
                        minPrice = 800;
                    }
                    maxPrice = 1000000000;
                }

                int numBeds = -1;
                int radioButtonID = numBedroomsRadio.getCheckedRadioButtonId();
                if (radioButtonID > 0) {
                    numBeds = numBedroomsRadio.indexOfChild(numBedroomsRadio.findViewById(radioButtonID));
                }

                int numBaths = -1;
                radioButtonID = numBathroomRadio.getCheckedRadioButtonId();
                if (radioButtonID > 0) {
                    numBaths = numBathroomRadio.indexOfChild(numBedroomsRadio.findViewById(radioButtonID));
                }

                SampleData.setFilteredBuildings(new FilterData(addressText.getText().toString(), startDate, endDate, minPrice, maxPrice, numBeds, numBaths, ratingBar.getRating(), parkingSwitch.isChecked(), petSwitch.isChecked()));
                finish();
            }
        });
    }

    private void loadFilters() {
        addressText.setText(SampleData.currentFilters.address);
        if (SampleData.currentFilters.minPrice == 0) {
            priceCheck400.setChecked(true);
        } else {
            priceCheck400.setChecked(false);
        }

        if (SampleData.currentFilters.minPrice <= 400 && SampleData.currentFilters.maxPrice >= 800) {
            priceCheck400_800.setChecked(true);
        } else {
            priceCheck400_800.setChecked(false);
        }

        if (SampleData.currentFilters.maxPrice > 800) {
            priceCheck800.setChecked(true);
        } else {
            priceCheck800.setChecked(false);
        }

        switch (SampleData.currentFilters.bed) {
            case 1:
                numBedroomsRadio.check(R.id.filter_bed_radio_1);
                break;
            case 2:
                numBedroomsRadio.check(R.id.filter_bed_radio_2);
                break;
            case 3:
                numBedroomsRadio.check(R.id.filter_bed_radio_3);
                break;
            case 4:
                numBedroomsRadio.check(R.id.filter_bed_radio_4);
                break;
            case 5:
                numBedroomsRadio.check(R.id.filter_bed_radio_5);
                break;
        }

        switch (SampleData.currentFilters.bath) {
            case 1:
                numBathroomRadio.check(R.id.filter_bath_radio_1);
                break;
            case 2:
                numBathroomRadio.check(R.id.filter_bath_radio_2);
                break;
            case 3:
                numBathroomRadio.check(R.id.filter_bath_radio_3);
                break;
            case 4:
                numBathroomRadio.check(R.id.filter_bath_radio_4);
                break;
            case 5:
                numBathroomRadio.check(R.id.filter_bath_radio_5);
                break;
        }

        ratingBar.setRating((float) SampleData.currentFilters.rating);

        parkingSwitch.setChecked(SampleData.currentFilters.parking);
        petSwitch.setChecked(SampleData.currentFilters.pets);

        Calendar cal = Calendar.getInstance();
        cal.setTime(SampleData.currentFilters.startDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        startDateText.setText((month + 1) + "/" + day + "/" + year);
        startDate = SampleData.currentFilters.startDate;

        cal.setTime(SampleData.currentFilters.endDate);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        endDateText.setText((month + 1) + "/" + day + "/" + year);
        endDate = SampleData.currentFilters.endDate;
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
}
