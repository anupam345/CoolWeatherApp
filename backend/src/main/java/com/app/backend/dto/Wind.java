package com.app.backend.dto; 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wind{
    public double speed;
    public int deg;
    public double gust;
}
