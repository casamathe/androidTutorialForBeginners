package com.example.crochetapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RowCounterActivity extends AppCompatActivity {

    private static final String PREFS = "counter";
    private static final String KEY_ROWS = "rows";
    private static final String KEY_STITCHES = "stitches";

    private SharedPreferences prefs;
    private int rows;
    private int stitches;
    private TextView txtRows;
    private TextView txtStitches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        rows = prefs.getInt(KEY_ROWS, 0);
        stitches = prefs.getInt(KEY_STITCHES, 0);
        txtRows = findViewById(R.id.txtRows);
        txtStitches = findViewById(R.id.txtStitches);

        findViewById(R.id.btnAddRow).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { rows++; stitches = 0; update(); }
        });
        findViewById(R.id.btnRemoveRow).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { if (rows > 0) rows--; update(); }
        });
        findViewById(R.id.btnAddStitch).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { stitches++; update(); }
        });
        findViewById(R.id.btnRemoveStitch).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { if (stitches > 0) stitches--; update(); }
        });
        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { rows = 0; stitches = 0; update(); }
        });
        update();
    }

    private void update() {
        txtRows.setText(String.valueOf(rows));
        txtStitches.setText(String.valueOf(stitches));
        prefs.edit().putInt(KEY_ROWS, rows).putInt(KEY_STITCHES, stitches).apply();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
