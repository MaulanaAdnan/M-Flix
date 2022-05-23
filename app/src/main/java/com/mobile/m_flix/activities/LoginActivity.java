package com.mobile.m_flix.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import com.mobile.m_flix.R;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.mobile.m_flix.database.DatabaseHelper;
import com.mobile.m_flix.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        binding.btnLogin.setOnClickListener(view -> {
            String username = binding.edtUsername.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();
            boolean res = databaseHelper.checkUser(username, password);

            if (res) {
                Toast.makeText(LoginActivity.this, "Succesfully Logged In As " + username, Toast.LENGTH_SHORT).show();
                Intent contentIntent = new Intent(LoginActivity.this, M_FlixActivity.class);
                startActivity(contentIntent);
                binding.edtUsername.setText("");
                binding.edtPassword.setText("");
            } else {
                Toast.makeText(LoginActivity.this,"Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
            }
        });

        binding.SignUp.setOnClickListener(view -> {
            Intent RegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(RegisterIntent);
            binding.edtUsername.setText("");
            binding.edtPassword.setText("");
        });
    }

}