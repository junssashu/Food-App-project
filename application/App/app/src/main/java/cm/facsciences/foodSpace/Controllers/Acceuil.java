package cm.facsciences.foodSpace.Controllers;

import android.app.ProgressDialog;
<<<<<<< HEAD
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cm.facsciences.foodSpace.ApiAdapters.RandomRecipeAdapter;
import cm.facsciences.foodSpace.ApiListeners.RandomRecipeApiResponseListener;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cm.facsciences.foodSpace.ApiAdapters.RandomRecipeAdapter;
import cm.facsciences.foodSpace.ApiListeners.RandomRecipeApiResponseListener;
import cm.facsciences.foodSpace.ApiListeners.RecipeClickListener;
>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04
import cm.facsciences.foodSpace.ApiTools.RandomRecipeApiResponse;
import cm.facsciences.foodSpace.R;
import cm.facsciences.foodSpace.RecipeDetailsActivity;

public class Acceuil extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter adapter;
    RecyclerView RecyclerView;
    Spinner spinner;
    List<String> tags = new ArrayList<>();
    SearchView mSearchView;

<<<<<<< HEAD
public class Acceuil extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter adapter;
    RecyclerView RecyclerView;

=======
>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
<<<<<<< HEAD
        manager = new RequestManager(this);
        manager.getRandomRecipes(listener);
        dialog.show();
=======
        spinner=findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter= ArrayAdapter.createFromResource(this, R.array.tags, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListenner);
        manager = new RequestManager(this);
        mSearchView = findViewById(R.id.searchView);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(listener,tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        /* manager.getRandomRecipes(listener);
        dialog.show();*/
>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04

    }

    private final RandomRecipeApiResponseListener listener= new RandomRecipeApiResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
             dialog.dismiss();
             RecyclerView = findViewById(R.id.list_foods);
             RecyclerView.setHasFixedSize(true);
             RecyclerView.setLayoutManager(new GridLayoutManager(Acceuil.this, 1));
<<<<<<< HEAD
             adapter = new RandomRecipeAdapter(Acceuil.this, response.recipes);
             RecyclerView.setAdapter(adapter);

        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(Acceuil.this, "Error", Toast.LENGTH_SHORT).show();
=======
             adapter = new RandomRecipeAdapter(Acceuil.this, response.recipes, mRecipeClickListener);
             RecyclerView.setAdapter(adapter);

        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(Acceuil.this, "Error", Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListenner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            manager.getRandomRecipes(listener, tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final RecipeClickListener mRecipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeclicked(String id) {
            startActivity(new Intent(Acceuil.this, RecipeDetailsActivity.class)
                    .putExtra("id", id ));

>>>>>>> 419cba27d299eab133986b1d4b5e98cc6b241e04
        }
    };
}
