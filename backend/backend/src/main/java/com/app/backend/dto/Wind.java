package com.app.backend.dto; 
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wind implements Serializable{
    public double speed;
    public int deg;
    public double gust;
}
