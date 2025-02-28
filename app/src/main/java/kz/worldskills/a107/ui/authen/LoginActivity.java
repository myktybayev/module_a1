package kz.worldskills.a107.ui.authen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kz.worldskills.a107.MainActivity;
import kz.worldskills.a107.R;

public class LoginActivity extends AppCompatActivity {
    Button buttonRegistration, btnLogin;
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Авторизация");

        btnLogin = findViewById(R.id.btnLogin);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String pass = editTextPassword.getText().toString();

                if(email.length()==0 || pass.length()==0 ){
                    Toast.makeText(LoginActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.contains("@") && email.contains("gmail") && email.contains(".com"))){
                    Toast.makeText(LoginActivity.this, "Введите корректную почту", Toast.LENGTH_SHORT).show();
                }

                else{

                    checkJSONAccount(email, pass);
                    
                }

            }
        });

        buttonRegistration = findViewById(R.id.buttonRegistration);
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }
    
    public void checkJSONAccount(String uEmail, String uPassword){
        String jsonString = LoadJSONAsset.loadJSONFromAsset(this, "login.json");
        if (jsonString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                boolean loginTabildi = false;
                
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String email = jsonObject.getString("mail");
                    String password = jsonObject.getString("password");
                    
                    if(email.equals(uEmail) && password.equals(uPassword)){
                        loginTabildi = true;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                if(loginTabildi == false){
                    Toast.makeText(this, "Not found user from JSON file", Toast.LENGTH_SHORT).show();
                }
                
                

            } catch (JSONException e) {

                Log.i("json_text", "e: "+e.toString());
                e.printStackTrace();
            }
        }
    }
}