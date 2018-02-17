package com.juliedeng.pokedex;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    PokemonAdapter(ArrayList<Pokemon> pokemons, Context context) {
        this.context = context;
        this.pokemons = pokemons;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_view,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(pokemons.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView avatar;

        public CustomViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            avatar = itemView.findViewById(R.id.avatar);
        }

        void bind(Pokemon pokemon) {
            name.setText(pokemon.getName());

            final String pokeName = pokemon.getName();
            final String pokeNumber = pokemon.getNumber();
            final String pokeAttack = Integer.toString(pokemon.getAttack());
            final String pokeDefense = Integer.toString(pokemon.getDefense());
            final String pokeHP = Integer.toString(pokemon.getHp());
            final String[] pokeTypes = pokemon.getTypes();

            Glide.with(context).load("http://img.pokemondb.net/artwork/" + pokemon.getName().toLowerCase() + ".jpg").into(avatar);
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ProfileActivity.class);
                    i.putExtra("name", pokeName);
                    i.putExtra("number", pokeNumber);
                    i.putExtra("attack", pokeAttack);
                    i.putExtra("defense", pokeDefense);
                    i.putExtra("hp", pokeHP);
                    i.putExtra("types", pokeTypes);
                    i.putExtra("image", "http://img.pokemondb.net/artwork/" + pokeName.toLowerCase() + ".jpg");
                    context.startActivity(i);
                }
            });
        }
    }
}
