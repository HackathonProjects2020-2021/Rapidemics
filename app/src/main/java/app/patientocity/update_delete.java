package app.patientocity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class update_delete extends AppCompatActivity {

    EditText name;
    EditText symptom;
    EditText diag;
    EditText room;
    EditText datetime;
    DatabaseReference ref;
    TextView key1;
//    EditText dis1;
////    EditText dis2;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        reference = FirebaseDatabase.getInstance().getReference("patients");

        name = (EditText) findViewById(R.id.name);
        symptom = (EditText) findViewById(R.id.symptom);
        diag = (EditText) findViewById(R.id.diag);
        room = (EditText) findViewById(R.id.room);
        datetime = (EditText) findViewById(R.id.date);
//        dis1 = (EditText) findViewById(R.id.dis1);
//        dis2 = (EditText) findViewById(R.id.dis2);


        String key =  getIntent().getExtras().get("key").toString();

        ref= FirebaseDatabase.getInstance().getReference().child("patients");
        key1 =(TextView) findViewById(R.id.key);
        key1.setText(key);

        showAllUserData();

    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String NAME = intent.getStringExtra("Name");
        String SYMPTOM = intent.getStringExtra("Symptom");
        String DIAGNOSIS = intent.getStringExtra("Diagnosis");
        String ROOM = intent.getStringExtra("Room");
        String DATETIME = intent.getStringExtra("Date/Time");
        String DIS1 = intent.getStringExtra("Disease 1");
        String DIS2 = intent.getStringExtra("Disease 2");


        name.setText(NAME);
        symptom.setText(SYMPTOM);
        diag.setText(DIAGNOSIS);
        room.setText(ROOM);
        datetime.setText(DATETIME);
//        dis1.setText(DIS1);
//        dis2.setText(DIS2);

    }

    public void btnDelete_Click(View view) {
        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(update_delete.this,"Deleted",Toast.LENGTH_LONG).show();
                    update_delete.this.finish();
                }
                else{
                    Toast.makeText(update_delete.this,"Not Deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void btnUpdate_Click(View view) {

        reference.child(getIntent().getStringExtra("key")).child("patientName").setValue(name.getText().toString());
        reference.child(getIntent().getStringExtra("key")).child("patientSymptom").setValue(symptom.getText().toString());
        reference.child(getIntent().getStringExtra("key")).child("patientDiag").setValue(diag.getText().toString());
        reference.child(getIntent().getStringExtra("key")).child("patientRoom").setValue(room.getText().toString());
        reference.child(getIntent().getStringExtra("key")).child("dateTime").setValue(datetime.getText().toString());
//        reference.child(getIntent().getStringExtra("key")).child("dis1").setValue(dis1.getText().toString());
//        reference.child(getIntent().getStringExtra("key")).child("dis2").setValue(dis2.getText().toString());
        reference.child(getIntent().getStringExtra("key")).child("symptomsHistory").child(datetime.getText().toString()).setValue(symptom.getText().toString());


        Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
    }


}