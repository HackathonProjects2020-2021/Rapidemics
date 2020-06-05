package app.patientocity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    GridLayout mainGrid;
    Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mainGrid = (GridLayout)findViewById(R.id.dash);

        setSingleEvent(mainGrid);
    }




    private void setSingleEvent(GridLayout mainGrid){
        for(int i=0; i<mainGrid.getChildCount();i++){
            CardView cardView  =(CardView) mainGrid.getChildAt(i);
            final int finalI =i;
            cardView.setOnClickListener((view)->{
                    if(finalI==0){
                        Intent intent = new Intent(Dashboard.this,addReports.class);
                        startActivity(intent);
                    }
                    else if(finalI==1){
                        Intent intent = new Intent(Dashboard.this,viewReports.class);
                        startActivity(intent);
                    }
                    else if(finalI==2){
                        //change navigation to monitors page later
                        Intent intent = new Intent(Dashboard.this,viewReports.class);
                        startActivity(intent);
                    }
                    else if(finalI==3){
                        Intent intent = new Intent(Dashboard.this,About.class);
                        startActivity(intent);
                    }
//                    else if(finalI==4){
//                        Intent intent = new Intent(Dashboard.this,Profile.class);
//                        startActivity(intent);
//                    }
                    else if(finalI==5){
                        Intent intent = new Intent(Dashboard.this,WelcomeActivity.class);
                        startActivity(intent);
                    }
                });
        }
    }
}