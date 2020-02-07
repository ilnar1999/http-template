package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonFetchingServiceImpl implements PokemonFetchingService {
    @Override
    public Pokemon fetchByName(String name) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;
        BufferedReader reader;
        ObjectMapper objectMapper;
        Pokemon pokemon;
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.addRequestProperty("User-Agent","");
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            objectMapper = new ObjectMapperFactoryImpl().getObjectMapper();
            pokemon = objectMapper.readValue(reader.readLine(), Pokemon.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("The name of the pokemon is incorrect!");
        }
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) {
        byte[] bytes = null;
        String url = fetchByName(name).getImageUrl();
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            bytes = new byte[connection.getContentLength()];
            connection.getInputStream().read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
