package ca.akshatpatel.earthquakeoccurrences;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText display_quake;
    Spinner sort_spinner;
    CalendarView calendar;
    Button submit;

    //Set default spinner selection
    String spinnerSelection = "magnitude";
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initialize variables
        display_quake = findViewById(R.id.number_to_display);
        submit = findViewById(R.id.sub_button);
        calendar = findViewById(R.id.calendarView);
        sort_spinner = findViewById(R.id.sort_spinner);
        date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).format(new Date());

        //Set spinner values
        sort_spinner.setAdapter(new ArrayAdapter<>(this, R.layout.custom_spinner, new String[]{"magnitude", "date"}));

        // Add an on item selected listener
        sort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    spinnerSelection = "magnitude";
                    return;
                }
                spinnerSelection = "time";

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + (month+1) + "-" + dayOfMonth;
            }
        });


        //Set on click listener for button
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent firstIntent = new Intent(MainActivity.this, EarthquakeListActivity.class);
                firstIntent.putExtra("order", spinnerSelection);
                firstIntent.putExtra("limit", display_quake.getText().toString());
                firstIntent.putExtra("start", date);

                startActivity(firstIntent);
            }
        });

    }
}
