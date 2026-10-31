package com.example.crochetapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HookSizeActivity extends AppCompatActivity {

    // {metric size, US size, typical use}
    private static final String[][] HOOKS = {
        {"2.0 mm", "B-1", "Fine thread, lace, amigurumi (fingering yarn)"},
        {"2.5 mm", "C-2", "Lightweight yarn, socks, small toys"},
        {"3.0 mm", "D-3", "Sport weight yarn, baby clothes"},
        {"3.5 mm", "E-4", "DK / light worsted yarn"},
        {"4.0 mm", "G-6", "Light worsted yarn, garments"},
        {"4.5 mm", "7", "Worsted weight yarn, blankets"},
        {"5.0 mm", "H-8", "Worsted weight yarn, hats and scarves"},
        {"5.5 mm", "I-9", "Aran / worsted weight yarn, bags"},
        {"6.0 mm", "J-10", "Chunky yarn, quick projects"},
        {"6.5 mm", "K-10.5", "Chunky yarn, cozy throws"},
        {"8.0 mm", "L-11", "Bulky yarn, thick blankets"},
        {"9.0 mm", "M/N-13", "Super bulky yarn"},
        {"15.0 mm", "P-16", "Jumbo yarn, arm-knit style crochet"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView list = findViewById(R.id.list);
        list.setAdapter(new BaseAdapter() {
            @Override public int getCount() { return HOOKS.length; }
            @Override public Object getItem(int i) { return HOOKS[i]; }
            @Override public long getItemId(int i) { return i; }

            @Override
            public View getView(int i, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(HookSizeActivity.this)
                            .inflate(R.layout.item_row, parent, false);
                }
                String[] h = HOOKS[i];
                ((TextView) convertView.findViewById(R.id.title)).setText(h[0] + "  |  US " + h[1]);
                ((TextView) convertView.findViewById(R.id.subtitle)).setText(h[2]);
                return convertView;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
