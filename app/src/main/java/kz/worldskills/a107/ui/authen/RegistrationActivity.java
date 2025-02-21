package kz.worldskills.a107.ui.authen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kz.worldskills.a107.MainActivity;
import kz.worldskills.a107.R;

public class RegistrationActivity extends AppCompatActivity {
    Button btnCreate;
    EditText editTextLogin, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Регистрация");
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        /*


         */
        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();

                if(editTextLogin.getText().length()== 0 || email.length()==0 || editTextPassword.getText().length()==0 ){
                    Toast.makeText(RegistrationActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.contains("@") && email.contains("gmail") && email.contains(".com"))){
                    Toast.makeText(RegistrationActivity.this, "Введите корректную почту", Toast.LENGTH_SHORT).show();
                }

                else{
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}