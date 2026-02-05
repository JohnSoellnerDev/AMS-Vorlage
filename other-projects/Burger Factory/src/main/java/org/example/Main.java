package org.example;

import org.example.factory.KingBurgerFactory;
import org.example.factory.McBurgerFactory;
import org.example.interfaces.BurgerFactory;
import org.example.sevices.BestellService;

public class Main {
    void main() {
        System.out.println("McBurger Bestellung:");
        BurgerFactory mcFactory = new McBurgerFactory();
        BestellService mcService = new BestellService(mcFactory);
        mcService.bestelleMenue();

        System.out.println();

        System.out.println("KingBurger Bestellung:");
        BurgerFactory kingFactory = new KingBurgerFactory();
        BestellService kingService = new BestellService(kingFactory);
        kingService.bestelleMenue();
    }
}
