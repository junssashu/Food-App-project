package cm.facsciences.foodSpace.Controllers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cm.facsciences.foodSpace.ApiAdapters.RandomRecipeAdapter;
import cm.facsciences.foodSpace.ApiListeners.RandomRecipeApiResponseListener;
import cm.facsciences.foodSpace.ApiTools.RandomRecipeApiResponse;
import cm.facsciences.foodSpace.R;

public class Acceuil extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter adapter;
    RecyclerView RecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        manager = new RequestManager(this);
        manager.getRandomRecipes(listener);
        dialog.show();

    }

    private final RandomRecipeApiResponseListener listener= new RandomRecipeApiResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
             dialog.dismiss();
             RecyclerView = findViewById(R.id.list_foods);
             RecyclerView.setHasFixedSize(true);
             RecyclerView.setLayoutManager(new GridLayoutManager(Acceuil.this, 1));
             adapter = new RandomRecipeAdapter(Acceuil.this, response.recipes);
             RecyclerView.setAdapter(adapter);

        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(Acceuil.this, "Error", Toast.LENGTH_SHORT).show();
        }
    };
}
