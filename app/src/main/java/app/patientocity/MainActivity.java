package app.patientocity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Handler handler;
//    Runnable runnable;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);        super.onCreate(savedInstanceState);


        img = findViewById(R.id.img);
        img.animate().alpha(4000).setDuration(0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(dsp);
                finish();
            }
        },4000);
    }
}