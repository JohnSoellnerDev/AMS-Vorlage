package org.example.entity;

import org.example.interfaces.Getraenk;

public class KingGetraenk implements Getraenk {
    @Override
    public void fuellen() {
        System.out.println("Fülle King Getränk nach");
    }
}
