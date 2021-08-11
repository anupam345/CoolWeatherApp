package com.app.backend.dto; 
import java.util.List; 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lists{
    public int dt;
    public Main main;
    public List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public int visibility;
    public double pop;
    public Sys sys;
    public String dt_txt;
}
