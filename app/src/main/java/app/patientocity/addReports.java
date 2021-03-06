package app.patientocity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class addReports extends AppCompatActivity {

    EditText name;
    EditText symptom;
    EditText diag;
    EditText room;
    EditText datetime;
    EditText time;
    EditText dis1;
    EditText dis2;
    Button add;


    DatabaseReference db_patients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reports);

        db_patients = FirebaseDatabase.getInstance().getReference("patients");

        name = (EditText) findViewById(R.id.name);
        symptom = (EditText) findViewById(R.id.symptom);
        diag = (EditText) findViewById(R.id.diag);
        room = (EditText) findViewById(R.id.room);
        datetime = (EditText) findViewById(R.id.room);
//        dis1 = (EditText) findViewById(R.id.dis1);
//        dis2 = (EditText) findViewById(R.id.dis2);



        add = (Button) findViewById(R.id.create);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReport();
            }
        });

    }

    private void addReport() {
        String n = name.getText().toString().trim();
        String s = symptom.getText().toString().trim();
        String d = diag.getText().toString().trim();
        String r = room.getText().toString().trim();
        String dati = datetime.getText().toString().trim();
//        String disease1 = dis1.getText().toString().trim();
//        String disease2 = dis2.getText().toString().trim();

        if (!TextUtils.isEmpty(n)) {
            String id = db_patients.push().getKey();
            patient p = new patient(id, n, s, d, r,dati);
            db_patients.child(id).setValue(p);
            db_patients.child(id).child("symptomsHistory").child(dati).setValue(s);
            name.setText("");
            symptom.setText("");
            diag.setText("");
            room.setText("");
            datetime.setText("");
//            dis1.setText("");
//            dis2.setText("");

            Toast.makeText(this, "Patient Report Added, Doc", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a name, Doc", Toast.LENGTH_LONG).show();
        }
    }



}
