package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonFetchingServiceImpl implements PokemonFetchingService {
    @SneakyThrows
    @Override
    public Pokemon fetchByName(String name) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;
        BufferedReader reader;
        ObjectMapper objectMapper;
        Pokemon pokemon;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.addRequestProperty("User-Agent","");
        connection.connect();

        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        objectMapper = new ObjectMapperFactoryImpl().getObjectMapper();
        pokemon = objectMapper.readValue(reader.readLine(), Pokemon.class);
        return pokemon;
    }

    @SneakyThrows
    @Override
    public byte[] getPokemonImage(String name) {
        byte[] bytes;
        String url = fetchByName(name).getImageUrl();
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        bytes = new byte[connection.getContentLength()];
        connection.getInputStream().read(bytes);
        return bytes;
    }
}
