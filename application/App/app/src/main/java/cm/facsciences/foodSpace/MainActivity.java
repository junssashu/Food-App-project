

package cm.facsciences.foodSpace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import cm.facsciences.foodSpace.Controllers.Register;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    Button sendCode;
    TextView verifyMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void envoyer (View v){

        Intent intent = new Intent(new Intent(getApplicationContext(), Register.class));
        startActivity(intent);
    }

}