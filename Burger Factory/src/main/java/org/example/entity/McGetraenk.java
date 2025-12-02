package org.example.entity;

import org.example.interfaces.Getraenk;

public class McGetraenk implements Getraenk {
    @Override
    public void fuellen() {
        System.out.println("Fülle MC Getränk nach");
    }
}
