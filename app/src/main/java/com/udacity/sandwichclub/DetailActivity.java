package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        // Show the content for the name property
        TextView name = findViewById(R.id.name);
        name.setText(sandwich.getMainName());

        // Show the content for the alsoKnownAs property
        TextView alsoKnownAs = findViewById(R.id.known_as);
        List<String> alsoKnownList = sandwich.getAlsoKnownAs();
        String alsoKnownString = "";
        for (int i = 0; i < alsoKnownList.size(); i++) {
            alsoKnownString += alsoKnownList.get(i) ;
            if (i < alsoKnownList.size() - 1) {
                alsoKnownString += "\n";
            }
        }

        alsoKnownAs.setText(alsoKnownString);

        // Show the content for the placeOfOrigin property
        TextView placeOfOrigin = findViewById(R.id.place_of_origin);
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());

        // Show the content for the description property
        TextView description = findViewById(R.id.description_tv);
        description.setText(sandwich.getDescription());

        // Show the content for the ingredients property
        TextView ingredients = findViewById(R.id.ingredients_text);
        List<String> ingredientsList = sandwich.getIngredients();
        String ingredientsString = "";
        for (int i = 0; i < ingredientsList.size(); i++) {
            ingredientsString += ingredientsList.get(i);
            if (i < ingredientsList.size() - 1) {
                ingredientsString += "\n";
            }
        }

        ingredients.setText(ingredientsString);


        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

        Picasso.with(this).load(sandwich.getImage()).into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
    }

    private void populateUI() {

    }
}
