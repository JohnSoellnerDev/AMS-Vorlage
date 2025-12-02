package org.example.factory;

import org.example.entity.McBurger;
import org.example.entity.McGetraenk;
import org.example.interfaces.Burger;
import org.example.interfaces.BurgerFactory;
import org.example.interfaces.Getraenk;

public class McBurgerFactory implements BurgerFactory {
    @Override
    public Burger createBurger() {
        return new McBurger();
    }

    @Override
    public Getraenk createGetraenk() {
        return new McGetraenk();
    }
}

