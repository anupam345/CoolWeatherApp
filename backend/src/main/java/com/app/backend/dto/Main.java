package com.app.backend.dto; 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Main{
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int sea_level;
    public int grnd_level;
    public int humidity;
    public double temp_kf;
}
