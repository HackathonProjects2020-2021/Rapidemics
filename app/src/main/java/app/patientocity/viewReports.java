package app.patientocity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class viewReports extends AppCompatActivity {

     ListView lv;
     FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        lv = findViewById(R.id.list_view);
        Query query = FirebaseDatabase.getInstance().getReference("patients");

        FirebaseListOptions<patient> options = new FirebaseListOptions.Builder<patient>()
                .setLayout(R.layout.patient)
                .setLifecycleOwner(viewReports.this)
                .setQuery(query,patient.class)
                 .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView name =  v.findViewById(R.id.nameTextView);
                TextView symptom = v.findViewById(R.id.symptomsTextView);
                TextView diag  = v.findViewById(R.id.diagnosisTextView);
                TextView room  = v.findViewById(R.id.roomTextView);
                TextView datetime =   v.findViewById(R.id.datetimeTextView);


                patient p = (patient) model;
                name.setText("Name:"+p.getPatientName().toString());
                symptom.setText("Symptoms:"+p.getPatientSymptom().toString());
                diag.setText("Diagnosis:"+p.getPatientDiag().toString());
                room.setText("Room:"+p.getPatientRoom().toString());
                datetime.setText("Date/Time:"+p.getDateTime().toString());




            }
        };
        lv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}