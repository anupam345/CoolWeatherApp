package com.app.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City{
    public int id;
    public String name;
    public Coord coord;
    public String country;
    public int population;
    public int timezone;
    public int sunrise;
    public int sunset;
}
