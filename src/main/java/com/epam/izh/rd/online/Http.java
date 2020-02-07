package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;

import java.util.Arrays;

public class Http {
    public static void main(String[] args) {
        PokemonFetchingServiceImpl pokemonFetchingService = new PokemonFetchingServiceImpl();
        PokemonFightingClubServiceImpl pokemonFightingClubService = new PokemonFightingClubServiceImpl();
        System.out.println(Arrays.toString(pokemonFetchingService.getPokemonImage("ditto")));
    }
}
