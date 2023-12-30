package com.moujib.dents_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moujib.dents_mobile.api.LoginApi;
import com.moujib.dents_mobile.api.RetrofitStudent;
import com.moujib.dents_mobile.beans.Groupe;
import com.moujib.dents_mobile.beans.PW;
import com.moujib.dents_mobile.beans.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private long studentId;

    private EditText email, password;
    private Button loginButton;
    List<PW> pws;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
        loginButton = findViewById(R.id.btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the login method when the login button is clicked
                performLogin();
            }
        });
    }
    private void performLogin() {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a Student object for login
        Student student = new Student();
        student.setEmail(userEmail);
        student.setPassword(userPassword);
        LoginApi loginApi = RetrofitStudent.getClient().create(LoginApi.class);
        Call<Student> call = loginApi.login(student);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()) {
                    // Handle successful login response
                    Student loginResponse = response.body();
                    Log.d("response",loginResponse.toString());
                    if (loginResponse != null) {
                        // Student exists, do something
                        studentId = loginResponse.getId();
                        String  email=loginResponse.getEmail();

                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);

                        i.putExtra("studentId", studentId);
                        i.putExtra("email",  email);
                        startActivity(i);
                    }
                } else {
                    Log.d("response",response.toString());
                    // Handle unsuccessful login response
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.d("erreur",t.toString());
                // Handle failure (e.g., network issues)
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
