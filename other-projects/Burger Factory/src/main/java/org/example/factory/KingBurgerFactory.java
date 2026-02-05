package org.example.factory;

import org.example.entity.KingBurger;
import org.example.entity.KingGetraenk;
import org.example.interfaces.Burger;
import org.example.interfaces.BurgerFactory;
import org.example.interfaces.Getraenk;

public class KingBurgerFactory implements BurgerFactory {
    @Override
    public Burger createBurger() {
        return new KingBurger();
    }

    @Override
    public Getraenk createGetraenk() {
        return new KingGetraenk();
    }
}

