package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /* Constants - To hold  keys  that are needed to extract the information from JSON String */
    private static final String SANDWICH_NAME = "name";
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String SANDWICH_ALSO = "alsoKnownAs";
    private static final String SANDWICH_PLACE_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject sandWichObject;
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredientsList = new ArrayList<>();
        List<String> alsoKnownAsList = new ArrayList<>();

        try {
            //making the root JSON object from the JSON file
            sandWichObject = new JSONObject(json);

            //getting the Main jsonObject from root JSON object
            JSONObject jsonObjectName = sandWichObject.getJSONObject(SANDWICH_NAME);
            mainName = jsonObjectName.getString(SANDWICH_MAIN_NAME);
            placeOfOrigin = sandWichObject.getString(SANDWICH_PLACE_ORIGIN);
            description = sandWichObject.getString(SANDWICH_DESCRIPTION);
            image = sandWichObject.getString(SANDWICH_IMAGE);

            JSONArray alsoKnwArray = jsonObjectName.getJSONArray(SANDWICH_ALSO);
            if (alsoKnwArray != null) {
                for (int i = 0; i < alsoKnwArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnwArray.getString(i));
                }
            }

            JSONArray ingredientArray = sandWichObject.getJSONArray(SANDWICH_INGREDIENTS);
            if (ingredientArray != null) {
                for (int i = 0; i < ingredientArray.length(); i++) {
                    ingredientsList.add(ingredientArray.getString(i));
                }
            }

        } catch (JSONException e) {
            Log.e("Error In JSON", "Error in parssing ", e);
        }
        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
    }
}
