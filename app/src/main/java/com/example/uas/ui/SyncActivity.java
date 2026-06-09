package com.example.uas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uas.R;
import com.example.uas.data.AppRepository;

public class SyncActivity extends AppCompatActivity {

    private Button btnLanjut;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        btnLanjut = findViewById(R.id.btn_lanjut);

        btnLanjut.setEnabled(false);
        btnLanjut.setText("Menyinkronkan data...");

        repository = new AppRepository(getApplication());

        repository.fetchAndSaveData(
                new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btnLanjut.setEnabled(true);
                                btnLanjut.setText("Data tersimpan, lanjut");
                                Toast.makeText(SyncActivity.this, "Sinkronisasi berhasil!", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(SyncActivity.this, "Gagal sinkronisasi data. Periksa internet Anda.", Toast.LENGTH_LONG).show();
                                btnLanjut.setEnabled(true);
                                btnLanjut.setText("Lanjut offline");
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