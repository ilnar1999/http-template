package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;

public class Http {
    public static void main(String[] args) {
        PokemonFetchingServiceImpl pokemonFetchingService = new PokemonFetchingServiceImpl();
        PokemonFightingClubServiceImpl pokemonFightingClubService = new PokemonFightingClubServiceImpl();
        Pokemon pikachu = pokemonFetchingService.fetchByName("pikachu");
        Pokemon slowpoke = pokemonFetchingService.fetchByName("slowpoke");
        Pokemon winner = pokemonFightingClubService.doBattle(pikachu, slowpoke);
        pokemonFightingClubService.showWinner(winner);
    }
}
