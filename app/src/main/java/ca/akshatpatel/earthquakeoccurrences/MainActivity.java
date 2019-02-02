package ca.akshatpatel.earthquakeoccurrences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {

    private EditText display_quake;
    private TextView display_date;
    private Spinner sort_spinner;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
