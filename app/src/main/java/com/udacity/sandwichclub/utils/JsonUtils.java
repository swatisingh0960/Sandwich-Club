package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {
    private static final String Name = "name";
    private static final String Description = "description";
    private static final String Also_Known_As = "alsoKnownAs";
    private static final String Main_Name = "mainName";
    private static final String Place_of_Origin = "placeOfOrigin";
    private static final String Image = "image";
    private static final String Ingredients = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject data_sandwich = new JSONObject(json);
            JSONObject name = data_sandwich.getJSONObject(Name);
            String mainName = name.getString(Main_Name);

            JSONArray alsoKnownAs = name.getJSONArray(Also_Known_As);


            List<String> alsoknownlist = new ArrayList<>();
            for (int i=0; i<alsoKnownAs.length();i++){
                alsoknownlist.add(alsoKnownAs.getString(i));
            }

            String placeOfOrigin = data_sandwich.getString(Place_of_Origin);
            String description = data_sandwich.getString(Description);
            String image = data_sandwich.getString(Image);
            JSONArray ingredients = data_sandwich.getJSONArray(Ingredients);

            List<String> ingredients_list = new ArrayList<>();
            for (int i=0; i<ingredients.length();i++){
                ingredients_list.add(ingredients.getString(i));
            }

            Sandwich sandwich = new Sandwich(mainName,alsoknownlist,placeOfOrigin,description,image,ingredients_list);
            return sandwich;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
