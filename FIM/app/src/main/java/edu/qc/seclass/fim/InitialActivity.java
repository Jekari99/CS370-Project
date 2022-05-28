package edu.qc.seclass.fim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class InitialActivity extends AppCompatActivity {

    EditText email,password;
    TextView notNow;
    Button loginBtn,gotoRegister,logoutBtn;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_activity);
        FirebaseApp.initializeApp(this);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        gotoRegister = findViewById(R.id.gotoRegister);
        notNow = findViewById(R.id.notNow);



        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkField(email);
                checkField(password);

                if(valid){
                    fAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(InitialActivity.this, "Logged in Successfully", Toast.LENGTH_LONG).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(InitialActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

        // register button
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                startActivity(new Intent(getApplicationContext(), RegisterFormActivity.class));
            }
        });

        // not now clickable text
        notNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), UserActivity.class));
                // Make logout button INVISIBLE
            }
        });
    }

    public void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        // Extract data from the document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());

                // identify user access level
                if(documentSnapshot.getString("isAdmin") != null){
                    Toast.makeText(InitialActivity.this, "Logged in as Admin", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Admin.class));
                    finish();
                }
                if(documentSnapshot.getString("isUser") != null){
                    Toast.makeText(InitialActivity.this, "Logged in as User", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), UserActivity.class));
                    finish();
                }
            }
        });
    }
    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }
        return valid;
    }

    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), UserActivity.class));
            finish();
        }
    }
}