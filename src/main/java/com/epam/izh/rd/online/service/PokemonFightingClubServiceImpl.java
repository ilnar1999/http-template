package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        Pokemon isHitting = p1.getPokemonId() > p2.getPokemonId() ? p1 : p2;
        while (true) {
            if (isHitting == p1) {
                doDamage(p1,p2);
                if (p2.getHp() <= 0) {
                    return p1;
                }
                isHitting = p2;
            } else {
                doDamage(p2,p1);
                if (p1.getHp() <= 0) {
                    return p2;
                }
                isHitting = p1;
            }
        }
    }

    @Override
    public void showWinner(Pokemon winner) {

    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short attack = from.getAttack();
        float hp = to.getHp();
        short defense = to.getDefense();
        to.setHp((float) (hp - (attack - attack * defense / 100.0)));
    }
}
