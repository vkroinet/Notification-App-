package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name;
    TextView tvResult;
    Button btnSubmit;
    private String prevName ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);

        name = findViewById(R.id.name);
        tvResult = findViewById(R.id.tvResult);
        btnSubmit = findViewById(R.id.btnSubmit);

        //Log.d("onCreate1: ", sharedPreferences.getString("name", ""));
        btnSubmit.setOnClickListener(v -> {

                String currentName = name.getText().toString().trim();

                if (currentName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (currentName.equals(prevName)) {
                    tvResult.setText("You have entered: " + currentName + "\nThis is a duplicate data");
                    Log.d("DuplicateCheck", "Duplicate data");
                } else {
                    tvResult.setText("You have entered: " + currentName);
                }

             /*
             // No need to this code
             SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", currentName);
                editor.apply();*/

                prevName = currentName;

        });

    }
}