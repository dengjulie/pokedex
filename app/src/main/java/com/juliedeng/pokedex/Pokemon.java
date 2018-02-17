package com.juliedeng.pokedex;

/**
 * Created by juliedeng on 2/16/18.
 */

public class Pokemon {
    String name, number;
    int attack, defense, hp;
    String[] types;

    public Pokemon(String name, String number, int attack, int defense, int hp, String[] types) {
        this.name = name;
        this.number = number;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.types = types;
    }
    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }

    public String getNumber() {
        return number;
    }

    public String[] getTypes() {
        return types;
    }
}
