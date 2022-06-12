package cm.facsciences.foodSpace.Controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cm.facsciences.foodSpace.Models.Consultant;
import cm.facsciences.foodSpace.R;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mName, mEmail, mPwd, mAge;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    TextView loginBtn;
    ImageView registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_page);


        mName        = findViewById(R.id.idname);
        mEmail       = findViewById(R.id.idemail);
        mPwd         = findViewById(R.id.idpwd);
        mAge         = findViewById(R.id.idage);
        loginBtn    = findViewById(R.id.loginbutton);
        registerBtn = findViewById(R.id.btn_register);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user= database.getReference("user");

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String pwd = mPwd.getText().toString().trim();
                String name = mName.getText().toString();
                String age = mAge.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    mName.setError("Login is required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    mPwd.setError("password is required");
                    return;
                }
                if (pwd.length() < 6) {
                    mPwd.setError("Password must be >= 6 characters");
                    return;
                }


                final ProgressDialog mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("Please Waiting....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // check if is already a user
                        if (snapshot.child(mName.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(Register.this, "This user already exist....", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();
                            Consultant user = new Consultant (mEmail.getText().toString(), mPwd.getText().toString(), mAge.getText().toString());
                            table_user.child(mName.getText().toString()).setValue(user);
                            Toast.makeText(Register.this, "Sign up successful!....", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(new Intent(getApplicationContext(), Acceuil.class));
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(new Intent(getApplicationContext(), Login.class));
                        startActivity(intent);
                    }
                });



            }
        }