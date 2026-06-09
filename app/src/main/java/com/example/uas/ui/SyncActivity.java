package com.example.uas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uas.R;
import com.example.uas.data.AppRepository;

public class SyncActivity extends AppCompatActivity {

    private Button btnLanjut;
    private TextView tvStatus, tvSub;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        btnLanjut = findViewById(R.id.btn_lanjut);
        tvStatus = findViewById(R.id.tv_sync_status);
        tvSub = findViewById(R.id.tv_sync_sub);

        btnLanjut.setEnabled(false);
        btnLanjut.setText("CONNECTING...");
        tvStatus.setText("SYSTEM STARTUP...");
        tvSub.setText("ESTABLISHING LINK TO SERVER...");

        repository = new AppRepository(getApplication());

        repository.fetchAndSaveData(
                new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btnLanjut.setEnabled(true);
                                btnLanjut.setText("BOOT SYSTEM");
                                tvStatus.setText("SYSTEM READY");
                                tvSub.setText("DATA SYNC COMPLETE. SYSTEM ONLINE.");
                                Toast.makeText(SyncActivity.this, "Sync Successful!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SyncActivity.this, "Sync Failed. Check Connection.", Toast.LENGTH_LONG).show();
                                btnLanjut.setEnabled(true);
                                btnLanjut.setText("BOOT OFFLINE");
                                tvStatus.setText("OFFLINE MODE");
                                tvSub.setText("COULD NOT CONNECT. LOCAL DATA ONLY.");
                            }
                        });
                    }
                }
        );

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SyncActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}