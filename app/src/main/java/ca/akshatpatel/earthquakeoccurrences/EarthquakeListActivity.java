package ca.akshatpatel.earthquakeoccurrences;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeListActivity extends AppCompatActivity {


    ListView list;

    //Minimum magnitude is 3
    String stringURL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=3&starttime=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_list);

        Intent intent = getIntent();
        String intentExtra1 = intent.getStringExtra("order");
        String intentExtra3 = intent.getStringExtra("limit");
        String intentExtra4 = intent.getStringExtra("start");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.stringURL);
        stringBuilder.append(intentExtra4);
        stringBuilder.append("&limit=");
        stringBuilder.append(intentExtra3);
        stringBuilder.append("&orderby=");
        stringBuilder.append(intentExtra1);

        System.out.println(stringBuilder.toString());
        this.stringURL = stringBuilder.toString();
        new QuakeAsyncTask(this).execute(this.stringURL);
    }


    private static class QuakeAsyncTask extends AsyncTask<String, Void, List<String>> {

        private WeakReference<EarthquakeListActivity> mainActivity;


        // Create a weak reference to avoid potential memory leak
        QuakeAsyncTask(EarthquakeListActivity context) {
            this.mainActivity = new WeakReference<>(context);
        }

        @Override
        protected List<String> doInBackground(String... stringurl) {
            return Utils.fetchEarthquakeData(stringurl[0]);
        }


        @Override
        public void onPostExecute(List<String> postExecuteResult) {

            final EarthquakeListActivity activity = this.mainActivity.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            CustomListAdapter arrayAdapter = new CustomListAdapter(activity, postExecuteResult);
            activity.list = activity.findViewById(R.id.list);
            activity.list.setAdapter(arrayAdapter);


            activity.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TextView currentLng = view.findViewById(R.id.tv_Lng);
                    String stringLat = ((TextView) view.findViewById(R.id.tv_Lat)).getText().toString();
                    String stringLng = currentLng.getText().toString();


                    String url = "https://www.openstreetmap.org/?mlat=" + stringLat + "&mlon=" + stringLng + "#map=5/" + stringLat + "/" + stringLng;
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));

                }
            });
        }
    }
}
