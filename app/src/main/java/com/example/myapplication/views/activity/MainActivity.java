package com.example.myapplication.views.activity;

import android.content.Intent;
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
import androidx.lifecycle.LifecycleOwner;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.worker.DemoWorker;

public class MainActivity extends AppCompatActivity {
    private String prevName ="";
    private static WorkManager WorkManager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);

        //Log.d("onCreate1: ", sharedPreferences.getString("name", ""));
        binding.btnSubmit.setOnClickListener(v -> {

                String currentName = binding.name.getText().toString().trim();

                if (currentName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (currentName.equals(prevName)) {
                    binding.tvResult.setText("You have entered: " + currentName + "\nThis is a duplicate data");
                    Log.d("DuplicateCheck", "Duplicate data");
                } else {
                    binding.tvResult.setText("You have entered: " + currentName);
                }

             /*
             // No need to this code
             SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", currentName);
                editor.apply();*/

                prevName = currentName;

        });
        binding.btnTest1.setOnClickListener(v -> {
        startActivity(new Intent(this, UserActivity.class));

        });

        //initialize workmanger
        WorkManager = WorkManager.getInstance(this);
        doWork();

    }

    private void doWork(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(DemoWorker.class)
                        .setConstraints(constraints)
                        .build();

        WorkManager.enqueue(request);
        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(request.getId())
                .observe((LifecycleOwner) this, workInfo -> {
                    if (workInfo != null) {
                        WorkInfo.State state = workInfo.getState();
                        Log.d("WorkManager", "Work status: " + state.name());
                        if (state == WorkInfo.State.SUCCEEDED) {
                            Log.d("WorkManager", "Work finished successfully!");
                        } else if (state == WorkInfo.State.FAILED) {
                            Log.d("WorkManager", "Work failed!");
                        } else if (state == WorkInfo.State.CANCELLED) {
                            Log.d("WorkManager", "Work cancelled!");
                        }
                    }
                });
    }
}