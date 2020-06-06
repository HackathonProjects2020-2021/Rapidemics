package app.patientocity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewReports extends AppCompatActivity {

    private ListView listview;
    DatabaseReference databaseReference;
    List<patient> patientslist;

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot patientsnapshot : dataSnapshot.getChildren()){
                    patient p = patientsnapshot.getValue(patient.class);
                    patientslist.add(p);

                }
                patientInfoAdapter pInfoAdapter = new patientInfoAdapter(viewReports.this, patientslist);
                listview.setAdapter(pInfoAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        listview = findViewById(R.id.list_viewListView);

        databaseReference = FirebaseDatabase.getInstance().getReference("Patient");

        patientslist = new ArrayList<>();



    }
}