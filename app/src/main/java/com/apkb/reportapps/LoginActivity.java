package com.apkb.reportapps;

import static com.apkb.reportapps.utils.Constant.passwordadmin;
import static com.apkb.reportapps.utils.Constant.passworduser;
import static com.apkb.reportapps.utils.Constant.usernameadmin;
import static com.apkb.reportapps.utils.Constant.usernameuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apkb.reportapps.ui.AdminActivity;
import com.apkb.reportapps.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText txtUser = findViewById(R.id.txtUser);
        final EditText txtPass = findViewById(R.id.txtPass);
        Button login = findViewById(R.id.Login);

        login.setOnClickListener(view -> {
            if (txtUser.getText().toString().equalsIgnoreCase(usernameadmin)&& txtPass.getText().toString().equalsIgnoreCase(passwordadmin)) {
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            } else if (txtUser.getText().toString().equalsIgnoreCase(usernameuser)&& txtPass.getText().toString().equalsIgnoreCase(passworduser)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else{
                Toast.makeText(LoginActivity.this, "username/password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}