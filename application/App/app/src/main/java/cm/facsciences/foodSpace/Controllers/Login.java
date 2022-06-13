package cm.facsciences.foodSpace.Controllers;

import static cm.facsciences.foodSpace.Controllers.Register.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cm.facsciences.foodSpace.MainActivity;
import cm.facsciences.foodSpace.Models.Consultant;
import cm.facsciences.foodSpace.R;

public class Login extends AppCompatActivity {

    EditText mLogin, mPwd;
    RelativeLayout mLoginBtn;
    TextView mRecover;
    ImageView backButton, showPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_page);

        mLogin = findViewById(R.id.idlogin);
        mPwd = findViewById(R.id.idpwd);
        mLoginBtn = findViewById(R.id.loginBtn);
        mRecover = findViewById(R.id.recoverid);
        backButton = findViewById(R.id.back_button);
        showPassword = findViewById(R.id.hidePassword);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = mLogin.getText().toString();
                String pwd = mPwd.getText().toString();

                if (TextUtils.isEmpty(login)) {
                    mLogin.setError("Login is required");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    mPwd.setError("password is required");
                    return;
                }

                final ProgressDialog mDialog = new ProgressDialog(Login.this);
                mDialog.setMessage("Please Waiting....");
                mDialog.show();



                // Authenticate the user

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.child(login).exists()){

                            mDialog.dismiss();
                            Consultant user = snapshot.child(login).getValue(Consultant.class);
                            if (user.getPassword().equals(pwd))
                            {
                                Toast.makeText(Login.this, "Sign In successfully....", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(new Intent(getApplicationContext(), Acceuil.class));
                                startActivity(intent);

                            }
                            else{
                                mDialog.dismiss();
                                Toast.makeText(Login.this, "Sign In unsuccessfully....", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(Login.this, "User don't exist ....", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.d(TAG, "onCancelled: Data base Error  ");

                    }
                });


            }

        });

        mRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }

        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }


}
