package ca.akshatpatel.earthquakeoccurrences;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class CustomListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private List<String> itemname1;

    public CustomListAdapter(Activity activity, List<String> itemnameA) {
        super(activity, R.layout.earthquake_list_item, itemnameA);
        this.context = activity;
        this.itemname1 = itemnameA;
    }


    @Override
    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.earthquake_list_item, null, true);

        //De-tokenize
        StringTokenizer tokens = new StringTokenizer(this.itemname1.get(position), "@");
        String titleToken = tokens.nextToken();
        String timeToken = tokens.nextToken();
        String urlToken = tokens.nextToken();
        String latToken = tokens.nextToken();
        String lngToken = tokens.nextToken();
        String magToken = tokens.nextToken();


        //Set Text for the list items
        TextView textInfo = rowView.findViewById(R.id.tv_title);
        TextView dateInfo = rowView.findViewById(R.id.tv_date);
        dateInfo.setText(new Date(Long.parseLong(timeToken)).toString());
        textInfo.setText(titleToken);


        //If greater than 5, change color to red
        Double magDouble = Double.valueOf(magToken);
        if (magDouble >= 5d) {
            textInfo.setBackgroundColor(Color.parseColor("#ff4546"));
            dateInfo.setBackgroundColor(Color.parseColor("#ff4546"));
        }


        //Set the hidden attributes
        ((TextView) rowView.findViewById(R.id.tv_URL)).setText(urlToken);
        ((TextView) rowView.findViewById(R.id.tv_Lat)).setText(latToken);
        ((TextView) rowView.findViewById(R.id.tv_Lng)).setText(lngToken);
        ((TextView) rowView.findViewById(R.id.tv_Mag)).setText(magToken);

        return rowView;
    }
}
