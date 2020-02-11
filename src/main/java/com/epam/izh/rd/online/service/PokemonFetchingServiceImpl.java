package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonFetchingServiceImpl implements PokemonFetchingService {

    private BufferedReader reader;
    private ObjectMapper objectMapper;
    private Pokemon pokemon;
    private HttpURLConnection connection;
    private final String url = "https://pokeapi.co/api/v2/";
    private byte[] imageBytes;

    @Override
    public Pokemon fetchByName(String name) {
        try {
            connection = (HttpURLConnection) new URL(url + "pokemon/" + name).openConnection();
            connection.addRequestProperty("User-Agent", "");
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            objectMapper = new ObjectMapperFactoryImpl().getObjectMapper();
            pokemon = objectMapper.readValue(reader.readLine(), Pokemon.class);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The pokemon name is incorrect!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) {
        String url = fetchByName(name).getImageUrl();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            imageBytes = new byte[connection.getContentLength()];
            connection.getInputStream().read(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return imageBytes;
    }
}
