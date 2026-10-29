package com.example.helloaab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView counter = findViewById(R.id.counter);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> counter.setText(getString(R.string.counter_format, ++count)));
    }
}
