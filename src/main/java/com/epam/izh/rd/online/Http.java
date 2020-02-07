package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;

public class Http {
    public static void main(String[] args) {
        PokemonFetchingServiceImpl pokemonFetchingService = new PokemonFetchingServiceImpl();
        PokemonFightingClubServiceImpl pokemonFightingClubService = new PokemonFightingClubServiceImpl();
        Pokemon firstPokemon = pokemonFetchingService.fetchByName("pikachu");
        Pokemon secondPokemon = pokemonFetchingService.fetchByName("slowpoke");
        Pokemon winner = pokemonFightingClubService.doBattle(firstPokemon, secondPokemon);
        pokemonFightingClubService.showWinner(winner);
    }
}
