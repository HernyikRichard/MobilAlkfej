package hu.mobilalkfejl.villanyorajelentes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int SECRET_KEY = 99;

    EditText emailEd;
    EditText personNameEd;
    EditText numberlEd;
    EditText passwordEd;
    Spinner spinner;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        int sec_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if (sec_key != 99)
            finish();
        initElement();
        auth = FirebaseAuth.getInstance();
    }

    private void initElement(){
        emailEd = findViewById(R.id.registEmailText);
        personNameEd = findViewById(R.id.registNameText);
        numberlEd = findViewById(R.id.registNumberText);
        passwordEd = findViewById(R.id.registPassText);
        spinner = findViewById(R.id.elofizetesTipus);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ugyfeltipus, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    public void cancel(View view) {
        finish();
    }

    public void resist(View view) {
        String registEmail = emailEd.getText().toString();
        String registPersonName = personNameEd.getText().toString();
        String registNumber = numberlEd.getText().toString();
        String registPassword = passwordEd.getText().toString();

        auth.createUserWithEmailAndPassword(registEmail,registPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    invoiceStart();
                }else {
                    Toast.makeText(RegistActivity.this, "probléma a bejelentkezessel",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void invoiceStart(){
        Intent intent = new Intent(this,DashBoard.class);
        //intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}