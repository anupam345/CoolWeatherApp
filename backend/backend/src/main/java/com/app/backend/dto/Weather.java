package com.app.backend.dto; 
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather implements Serializable{
    public int id;
    public String main;
    public String description;
    public String icon;
}
