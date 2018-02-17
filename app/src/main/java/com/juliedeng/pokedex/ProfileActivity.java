package com.juliedeng.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by juliedeng on 2/16/18.
 */

public class ProfileActivity extends AppCompatActivity {

    TextView name, number, attack, defense, hp, types;

    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();

        name = findViewById(R.id.profileName);
        number = findViewById(R.id.profileNumber);
        attack = findViewById(R.id.profileAttack);
        defense = findViewById(R.id.profileDefense);
        hp = findViewById(R.id.profileHP);
        types = findViewById(R.id.profileTypes);
        avatar = findViewById(R.id.profileAvatar);

        String[] pTypes = intent.getStringArrayExtra("types");
        String printTypes = "";
        for (String type : pTypes) {
            printTypes += type + " ";
        }
        name.setText(intent.getStringExtra("name"));
        number.setText("#: " + intent.getStringExtra("number"));
        attack.setText("Attack: " + intent.getStringExtra("attack"));
        defense.setText("Defense: " + intent.getStringExtra("defense"));
        hp.setText("HP: " + intent.getStringExtra("hp"));
        types.setText("Types: " + printTypes);

        Glide.with(getApplicationContext()).load(intent.getStringExtra("image")).into(avatar);

    }
}
