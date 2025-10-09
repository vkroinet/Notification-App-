package com.example.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.myapplication.worker.BootWorker;

import java.util.concurrent.TimeUnit;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

      /*  if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, BootService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
            }
        }*/

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Schedule WorkManager task
            OneTimeWorkRequest bootWork = new OneTimeWorkRequest.Builder(BootWorker.class)
                    .setInitialDelay(2, TimeUnit.SECONDS) // optional delay after boot
                    .build();

            WorkManager.getInstance(context).enqueue(bootWork);
        }
    }
}
