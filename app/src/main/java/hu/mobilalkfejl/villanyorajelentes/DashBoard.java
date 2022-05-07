package hu.mobilalkfejl.villanyorajelentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }

    public void oraAllasokInit(View view) {
        Intent intent = new Intent(this,OraAllasokActivity.class);
        startActivity(intent);
    }

    public void ujOraAllas(View view) {
        Intent intent = new Intent(this,UjoraAllasActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}