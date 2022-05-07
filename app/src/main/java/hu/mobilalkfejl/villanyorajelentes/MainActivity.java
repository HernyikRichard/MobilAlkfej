package hu.mobilalkfejl.villanyorajelentes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 99;


    private FirebaseAuth auth;
    EditText userEmailEt;
    EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        userEmailEt = findViewById(R.id.editTextUserEmail);
        passwordEt = findViewById(R.id.editTextPassword);
    }

    public void login(View v){
        String userEmail, password = "";

        userEmail = userEmailEt.getText().toString();
        password = passwordEt.getText().toString();

        Log.i(LOG_TAG, "beleptel "+userEmail+" jelszo: "+password);
        try {
            auth.signInWithEmailAndPassword(userEmail, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        invoiceStart();
                    }else {
                        Toast.makeText(MainActivity.this, "probléma a bejelentkezessel",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "probléma a bejelentkezessel",Toast.LENGTH_LONG).show();
        }
    }

    private void invoiceStart(){
        Intent intent = new Intent(this,DashBoard.class);
        //intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegistActivity.class);
        intent.putExtra("SECRET_KEY", 99);
        startActivity(intent);
    }

}