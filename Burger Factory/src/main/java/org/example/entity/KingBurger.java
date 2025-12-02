package org.example.entity;

import org.example.interfaces.Burger;

public class KingBurger implements Burger {
    @Override
    public void zubereiten() {
        System.out.println("Bereite KingBurger vor");
    }
}
