package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {

    private Pokemon attacker;
    private Pokemon defender;
    private PokemonFetchingService pokemonFetchingService;
    private byte[] imageBytes;
    private final String imagePath = "image.png";

    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        getAttackerAndDefender(p1, p2);
        swapAttackerAndDefender(attacker, defender); // swap here because next cycle starts with swap
        while (defender.isAlive()) {
            swapAttackerAndDefender(attacker, defender);
            doDamage(attacker, defender);
        }
        return attacker;
    }

    private void swapAttackerAndDefender(Pokemon attacker, Pokemon defender) {
        this.attacker = defender;
        this.defender = attacker;
    }

    private void getAttackerAndDefender(Pokemon p1, Pokemon p2) {
        if (p1.getPokemonId() > p2.getPokemonId()) {
            this.attacker = p1;
            this.defender = p2;
        } else {
            this.attacker = p2;
            this.defender = p1;
        }
    }

    @Override
    public void showWinner(Pokemon winner) {
        pokemonFetchingService = new PokemonFetchingServiceImpl();
        imageBytes = pokemonFetchingService.getPokemonImage(winner.getPokemonName());

        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(imagePath))) {
            out.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short attack = from.getAttack();
        float hp = to.getHp();
        short defense = to.getDefense();
        to.setHp((float) (hp - (attack - attack * defense / 100.0)));
    }
}
