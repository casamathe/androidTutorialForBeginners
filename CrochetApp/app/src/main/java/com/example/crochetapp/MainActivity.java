package com.example.crochetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open(R.id.btnStitches, StitchGuideActivity.class);
        open(R.id.btnCounter, RowCounterActivity.class);
        open(R.id.btnHooks, HookSizeActivity.class);
    }

    private void open(int buttonId, final Class<?> target) {
        findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, target));
            }
        });
    }
}
