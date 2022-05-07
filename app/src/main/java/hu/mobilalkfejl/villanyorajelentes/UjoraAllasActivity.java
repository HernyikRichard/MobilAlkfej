package hu.mobilalkfejl.villanyorajelentes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hu.mobilalkfejl.villanyorajelentes.model.VillanyoraJelentes;

public class UjoraAllasActivity extends AppCompatActivity {
    private static final String LOG_TAG = UjoraAllasActivity.class.getName();
    private CollectionReference mItems;
    private FirebaseAuth auth;
    private FirebaseFirestore mFirestore;
    private EditText mOraAllas;
    private EditText mOraAzonosito;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujora_allas);
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");
        user = FirebaseAuth.getInstance().getCurrentUser();

        initElems();
    }

    private void initElems() {
        mOraAllas = findViewById(R.id.oraAllas);
        mOraAzonosito = findViewById(R.id.oraAzonosito);
    }

    public void save(View view) {
        String lOraAzonosito = mOraAzonosito.getText().toString();
        String lOraAllas = mOraAllas.getText().toString();
        try {

            mItems.add(new VillanyoraJelentes(user.getEmail(),lOraAzonosito,lOraAllas,stringDate()));
            Toast.makeText(UjoraAllasActivity.this, "Sikeres Adatrögzítés",Toast.LENGTH_LONG).show();
            finish();
        }catch (Exception e){
            Log.e(LOG_TAG, e.getMessage());
        }
    }
    public String stringDate() {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        Date dateObj = calendar.getTime();
        String formattedDate = dtf.format(dateObj);
        return formattedDate;
    }

}