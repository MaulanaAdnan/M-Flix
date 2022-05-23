package com.mobile.m_flix.activities;

import android.os.Bundle;
import com.mobile.m_flix.R;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.mobile.m_flix.database.DatabaseHelper;
import com.mobile.m_flix.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.SignIn.setOnClickListener(view -> {
            Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(LoginIntent);
        });

        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.edtUsername.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();
            String confPass = binding.edtConfirmPassword.getText().toString().trim();

            if (password.equals(confPass)) {
                long val = databaseHelper.addUser(username, password);

                if (val > 0) {
                    Toast.makeText(RegisterActivity.this,"You have Register as " + username, Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(moveToLogin);
                    binding.edtUsername.setText("");
                    binding.edtPassword.setText("");
                    binding.edtConfirmPassword.setText("");
                }
            } else {
                Toast.makeText(RegisterActivity.this,"Password is Not Matching", Toast.LENGTH_SHORT).show();
            }
        });
    }
}