package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwhich;
        JSONObject name;
        String mainName;
        JSONArray alsoKnownAs;
        List<String> alsoKnownAsList;
        String placeOfOrigin;
        String description;
        String image;
        JSONArray ingredients;
        List<String> ingredientsList;
        Sandwich sandWhichSelected;

        try {
            // Initialize the JSONObject instance
            sandwhich = new JSONObject(json);

            // Extract the name property
            name = sandwhich.getJSONObject("name");

            // Extract mainName property
            mainName = name.getString("mainName");

            // Extract alsoKnownAs property
            alsoKnownAs = name.getJSONArray("alsoKnownAs");
            alsoKnownAsList = new ArrayList<String>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            // Extract the placeOfOrigin property
            placeOfOrigin = sandwhich.getString("placeOfOrigin");

            // Extract the description property
            description = sandwhich.getString("description");

            // Extract the image property
            image = sandwhich.getString("image");

            // Extract the ingredients property
            ingredients = sandwhich.getJSONArray("ingredients");
            ingredientsList = new ArrayList<String>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsList.add(ingredients.getString(i));
            }

            // Create sandwhich instance
            sandWhichSelected = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
            return sandWhichSelected;

        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }

        return null;
    }
}
