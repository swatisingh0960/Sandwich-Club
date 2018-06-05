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

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;
    private TextView tvAlsoKnownAs;
//    private TextView lblAlsoKnownAs;
    private TextView tvOrigin;
//    private TextView lblOrigin;
    private TextView tvDescription;
//    private TextView lblDescription;
    private TextView tvIngredients;
//    private TextView lblIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        tvAlsoKnownAs = findViewById(R.id.also_known_tv);
        tvOrigin =      findViewById(R.id.origin_tv);
        tvDescription = findViewById(R.id.description_tv);
        tvIngredients = findViewById(R.id.ingredients_tv);

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
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
                if (sandwich.getPlaceOfOrigin().isEmpty()){
                    tvOrigin.setText(R.string.no_data_found);
                }
                else{
                    tvOrigin.setText(sandwich.getPlaceOfOrigin());
                }
                if (sandwich.getAlsoKnownAs().isEmpty()){
                    tvAlsoKnownAs.setText(R.string.no_data_found);
                }
                else{
                    tvAlsoKnownAs.setText(listSandwich(sandwich.getAlsoKnownAs()));
                }

                tvDescription.setText(sandwich.getDescription());
                tvIngredients.setText(listSandwich(sandwich.getIngredients()));

        }

        public StringBuilder listSandwich(List<String> list){
           StringBuilder stringBuilder = new StringBuilder();
           for (int i=0;i<list.size();i++){
               stringBuilder.append(list.get(i)).append("\n");
           }
            return stringBuilder;
        }


}
