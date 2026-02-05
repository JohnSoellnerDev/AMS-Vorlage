package org.example.sevices;

import org.example.interfaces.Burger;
import org.example.interfaces.BurgerFactory;
import org.example.interfaces.Getraenk;

public class BestellService {
    private final BurgerFactory factory;

    public BestellService(BurgerFactory factory) {
        this.factory = factory;
    }

    public void bestelleMenue() {
        Burger burger = factory.createBurger();
        Getraenk getraenk = factory.createGetraenk();

        burger.zubereiten();
        getraenk.fuellen();
    }
}
