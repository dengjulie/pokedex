package com.juliedeng.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by juliedeng on 2/16/18.
 */

public class FilterActivity extends AppCompatActivity {
    CheckBox psychic;
    Button filterButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        intent.getStringArrayExtra("pokemons");

        psychic = findViewById(R.id.psychicType);

        filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = getApplicationContext();
                        Intent i = new Intent(context, FilterActivity.class);
                        i.putExtra("psychic", psychic.isChecked());
                        context.startActivity(i);
                    }
                });
    }
}
