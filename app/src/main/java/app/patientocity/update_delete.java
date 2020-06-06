package app.patientocity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        name = (EditText) findViewById(R.id.name);
        symptom = (EditText) findViewById(R.id.symptom);
        diag = (EditText) findViewById(R.id.diag);
        room = (EditText) findViewById(R.id.room);
        datetime = (EditText) findViewById(R.id.date);

        String key =  getIntent().getExtras().get("key").toString();

        ref= FirebaseDatabase.getInstance().getReference().child("patients");
        key1 =(TextView) findViewById(R.id.key);
        key1.setText(key);

        name.setText(getIntent().getStringExtra("Name"));
        symptom.setText(getIntent().getStringExtra("Symptom"));
        diag.setText(getIntent().getStringExtra("Diagnosis"));
        room.setText(getIntent().getStringExtra("Room"));
        datetime.setText(getIntent().getStringExtra("Date/Time"));





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
        ref.child()
    }
}