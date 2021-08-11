package com.app.backend.dto; 
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Coord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4104970306060028103L;
	public double lat;
    public double lon;
}
