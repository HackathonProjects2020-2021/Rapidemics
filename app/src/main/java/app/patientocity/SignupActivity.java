package app.patientocity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    public EditText email;
    public EditText password;
    FirebaseAuth mFirebaseAuth;
    TextView tvSignIn;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tvSignIn=findViewById(R.id.textView);
        mFirebaseAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password = findViewById(R.id.pass);
        signup = findViewById(R.id.create);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                if(e.isEmpty()){
                    email.setError("Enter an Email");
                    email.requestFocus();
                }
                else if(p.isEmpty()){
                    password.setError("Enter a Password");
                    password.requestFocus();
                }
                else if(e.isEmpty() && p.isEmpty()){
                    Toast.makeText(SignupActivity.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else  if(!(e.isEmpty() && p.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(SignupActivity.this,Dashboard.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignupActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}