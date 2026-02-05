package org.example.entity;

import org.example.interfaces.Burger;

public class McBurger implements Burger {
    @Override
    public void zubereiten() {
        System.out.println("Bereite McBurger vor");
    }
}
