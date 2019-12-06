package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import jbu3.campussubleasefinder.R;

public class FilterActivity extends AppCompatActivity {

    Button startDateButton;
    TextView startDateText;
    Button endDateButton;
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

        endDateButton = findViewById(R.id.filter_end_date_button);
        endDateText = findViewById(R.id.filter_end_date_text);

        startDateButton = findViewById(R.id.filter_start_date_button);
        startDateText = findViewById(R.id.filter_start_date_text);

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        startDate = new Date(datePicker.getCalendarView().getDate());
                        startDateText.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    endDate = new Date(datePicker.getCalendarView().getDate());
                    endDateText.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        EditText addressText = findViewById(R.id.filter_address_text);
        CheckBox priceCheck400 = findViewById(R.id.filter_price_400);
        CheckBox priceCheck400_800 = findViewById(R.id.filter_price_400_800);
        CheckBox priceCheck800 = findViewById(R.id.filter_price_800);

        RadioGroup numBedroomsRadio = findViewById(R.id.filter_bedrooms_radio);
        RadioGroup numBathroomRadio = findViewById(R.id.filter_bathrooms_radio);

        Switch parkingSwitch = findViewById(R.id.filter_parking_switch);
        CheckBox parkingPriceCheck50 = findViewById(R.id.filter_parking_price_50);
        CheckBox parkingPriceCheck50_80 = findViewById(R.id.filter_parking_price_50_80);
        CheckBox parkingPriceCheck80 = findViewById(R.id.filter_parking_price_80);

        Switch petSwitch = findViewById(R.id.filter_pet_switch);


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
