package hu.mobilalkfejl.villanyorajelentes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import hu.mobilalkfejl.villanyorajelentes.adapter.VillanyOraAdapter;
import hu.mobilalkfejl.villanyorajelentes.model.VillanyoraJelentes;

public class AdatModositas extends AppCompatActivity {
    private static final String LOG_TAG = AdatModositas.class.getName();
    private ArrayList<VillanyoraJelentes> mItemsData;
    private FirebaseFirestore mFirstor;
    private CollectionReference mItems;

    private RecyclerView mRecyclerView;
    private VillanyOraAdapter mAdapter;
    private int gridNumber = 1;
    private ListView mlistOraszamok;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data;
    private ListView listView;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_modositas);
        mItemsData = new ArrayList<>();
        mFirstor = FirebaseFirestore.getInstance();
        mItems = mFirstor.collection("Items");
        data = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        querryData();
    }
    private void querryData() {
        mItems.orderBy("datum", Query
                .Direction.DESCENDING)
                .limit(30)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot d : queryDocumentSnapshots){
                        VillanyoraJelentes v = d.toObject(VillanyoraJelentes.class);
                        if (v.getUserID().equals(user.getEmail())){
                            v.setId(d.getId());
                            data.add(v.toString());
                            mItemsData.add(v);
                        }
                    }
                });
    }

    public void listazasNullazashoz(View view) {
        kirajzol();
    }
    private void kirajzol() {
        adapter = new ArrayAdapter<String>(this,R.layout.list_item_element,data);
        listView = (ListView) findViewById(R.id.listOraszamok);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                DocumentReference ref = mItems.document(mItemsData.get(position)._getId());
                ref.update("oraAllas","0").addOnSuccessListener(success -> {
                    Toast.makeText(AdatModositas.this, "Sort sikeresen nulláztad", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
        });
    }
}