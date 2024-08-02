package com.example.tohand;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tohand.databinding.ActivityResultBinding;
import com.google.android.material.snackbar.Snackbar;

public class result extends AppCompatActivity {

    private static final String PREF_NAME = "phone_prefs";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_DEPOSITE = "Deposite";
    private AppBarConfiguration appBarConfiguration;
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_result);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedPhone = prefs.getString(KEY_PHONE, null);
        String savedDeposit = prefs.getString(KEY_DEPOSITE, null);

        if (savedPhone != null) {
            EditText phoneEditText = findViewById(R.id.PhoneEditText);
            phoneEditText.setText(savedPhone);
        }

        if (savedDeposit != null) {
            // Display the saved deposit value here
            Toast.makeText(this, "Saved deposit: " + savedDeposit, Toast.LENGTH_SHORT).show();
        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_result);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}