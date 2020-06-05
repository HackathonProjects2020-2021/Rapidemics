package app.patientocity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;

public class addReports extends AppCompatActivity {

    TextView text_pathShow;
    Button btn_filPick;
    Intent myFileIntent;
    EditText name;
    EditText symptom;
    EditText diag;
    EditText room;
    Button add;

    DatabaseReference db_patients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reports);

        db_patients= FirebaseDatabase.getInstance().getReference("patients");

        text_pathShow = (TextView)findViewById(R.id.text_pah);
        btn_filPick=(Button)findViewById(R.id.file);
        name= (EditText)findViewById(R.id.name);
        symptom=(EditText)findViewById(R.id.symptom);
        diag=(EditText)findViewById(R.id.diag);
        room=(EditText)findViewById(R.id.room);
        add = (Button)findViewById(R.id.create);



        btn_filPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent=new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,10);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReport();

            }
        });

    }

    private void addReport(){
        String n =name.getText().toString().trim();
        String s = symptom.getText().toString().trim();
        String d =diag.getText().toString().trim();
        String r = room.getText().toString().trim();

        if (!TextUtils.isEmpty(n)) {

            String id = db_patients.push().getKey();
            patient p = new patient(id,n,s,d,r);
            db_patients.child(id).setValue(p);
            name.setText("");
            symptom.setText("");
            diag.setText("");
            room.setText("");

            Toast.makeText(this, "Patient Report Added, Doctor", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }





//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 10:
//
//                if(resultCode==RESULT_OK){
//                    String path =data.getData().getPath();
//                    text_pathShow.setText(path);
//                }
//
//                    break;
//
//        }
//    }
}