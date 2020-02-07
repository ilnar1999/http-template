package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service
 * Для маппинка значений из массива stats рекомендуется использовать отдельный класс Stat и аннотацию @JsonCreator
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    /**
     * Уникальный идентификатор, маппится из поля pokemonId
     */
    @JsonProperty("id")
    private long pokemonId;

    /**
     * Имя покемона, маппится из поля pokemonName
     */
    @JsonProperty("name")
    private String pokemonName;

    /**
     * Здоровье покемона, маппится из массива объектов stats со значением name: "hp"
     */
    private float hp;

    /**
     * Атака покемона, маппится из массива объектов stats со значением name: "attack"
     */
    private short attack;

    /**
     * Защита покемона, маппится из массива объектов stats со значением name: "defense"
     */
    private short defense;

    private String imageUrl;

    @JsonCreator
    public Pokemon(@JsonProperty("stats") Stat[] stats, @JsonProperty("sprites") Sprite sprite) {
        this.hp = stats[5].getStat();
        this.attack = stats[4].getStat();
        this.defense = stats[3].getStat();
        this.imageUrl = sprite.getUrl();
    }
}
