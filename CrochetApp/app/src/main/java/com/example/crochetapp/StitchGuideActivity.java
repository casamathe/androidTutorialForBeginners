package com.example.crochetapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class StitchGuideActivity extends AppCompatActivity {

    private final List<Stitch> stitches = StitchData.all();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView list = findViewById(R.id.list);
        list.setAdapter(new BaseAdapter() {
            @Override public int getCount() { return stitches.size(); }
            @Override public Object getItem(int i) { return stitches.get(i); }
            @Override public long getItemId(int i) { return i; }

            @Override
            public View getView(int i, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(StitchGuideActivity.this)
                            .inflate(R.layout.item_row, parent, false);
                }
                Stitch s = stitches.get(i);
                ((TextView) convertView.findViewById(R.id.title)).setText(s.name + " (" + s.abbreviation + ")");
                ((TextView) convertView.findViewById(R.id.subtitle))
                        .setText(getString(R.string.difficulty, s.difficulty));
                return convertView;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stitch s = stitches.get(position);
                new AlertDialog.Builder(StitchGuideActivity.this)
                        .setTitle(s.name + " (" + s.abbreviation + ")")
                        .setMessage(s.howTo)
                        .setPositiveButton(R.string.close, null)
                        .show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
