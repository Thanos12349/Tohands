package com.example.tohand;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dueActivity extends AppCompatActivity {

    private static final String PREF_NAME = "phone_prefs";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_DEPOSITE = "Deposite";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due);

        EditText phone = findViewById(R.id.PhoneEditText);
        Button submit = findViewById(R.id.submitBtn);
        Intent fromAct = getIntent();
        String deposit = Integer.toString(fromAct.getIntExtra("due", -1));
        phone.setText("");
        // Load the phone number from shared preferences
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedPhone = prefs.getString(KEY_PHONE, null);
        String deposite = prefs.getString(KEY_DEPOSITE,null);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phone.getText().toString();

                Toast.makeText(dueActivity.this, "Phone number: " + phoneNumber, Toast.LENGTH_SHORT).show();
                Toast.makeText(dueActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                // Save the phone number to shared preferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(KEY_PHONE, phoneNumber);
                editor.putString(KEY_DEPOSITE,deposit);
                editor.apply();
            }
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }
}