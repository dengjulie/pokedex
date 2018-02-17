package com.juliedeng.pokedex;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    Button toggleView, filter, random;
    RecyclerView recyclerView;
    boolean gridView;
    ArrayList<Pokemon> pokemons;
    PokemonAdapter pokemonAdapter;

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("pokeData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemons = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(loadJSONFromAsset());
            Iterator<String> iterator = data.keys();

            String[] types;

            while (iterator.hasNext()) {
                String name = iterator.next();
                if (name.contains("(")) {
                    continue;
                }

                JSONObject pokemonData = data.getJSONObject(name);

                JSONArray jsonTypes = pokemonData.getJSONArray("Type");
                types = new String[jsonTypes.length()];
                for (int i = 0; i < jsonTypes.length(); i++) {
                    types[i] = jsonTypes.getString(i).trim();
                }
                pokemons.add(new Pokemon(
                        name,
                        pokemonData.getString("#").trim(),
                        Integer.parseInt(pokemonData.getString("Attack").trim()),
                        Integer.parseInt(pokemonData.getString("Defense").trim()),
                        Integer.parseInt(pokemonData.getString("HP").trim()),
                        types
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gridView = false;
        pokemonAdapter = new PokemonAdapter(pokemons, this);
        recyclerView.setAdapter(pokemonAdapter);

        toggleView = (Button) findViewById(R.id.toggleView);
        toggleView.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!gridView) {
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                        gridView = true;
                    } else {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        gridView = false;
                    }
                }
            });
        filter = findViewById(R.id.filter);
        filter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = getApplicationContext();
                        Intent i = new Intent(context, FilterActivity.class);
                        i.putExtra("pokemons", pokemons);
                        context.startActivity(i);

//                        ArrayList<Pokemon> filteredPokemons = new ArrayList(pokemons.subList(0, 20));
//                        pokemonAdapter.setPokemons(filteredPokemons);
//                        pokemonAdapter.notifyDataSetChanged();
                    }
                });
        random = findViewById(R.id.random);
        random.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Collections.shuffle(pokemons);
                        ArrayList<Pokemon> shuffledPokemons = new ArrayList(pokemons.subList(0, 20));
                        pokemonAdapter.setPokemons(shuffledPokemons);
                        pokemonAdapter.notifyDataSetChanged();
                    }
        });
    }

}
