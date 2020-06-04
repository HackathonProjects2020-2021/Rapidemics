package app.patientocity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class addReports extends AppCompatActivity {

    TextView text_pathShow;
    Button btn_filPick;
    Intent myFileIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reports);

        text_pathShow = (TextView)findViewById(R.id.text_pah);
        btn_filPick=(Button)findViewById(R.id.file);

        btn_filPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent=new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,10);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:

                if(resultCode==RESULT_OK){
                    String path =data.getData().getPath();
                    text_pathShow.setText(path);
                }

                    break;

        }
    }
}