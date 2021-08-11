
/*Data transfer object root element for calling open weather API
 * generated using online json to DTO generator
 * https://json2csharp.com/json-to-pojo
 * 
 * */
package com.app.backend.dto; 
import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Root implements Serializable{
	
    public String cod;
    public int message;
    public int cnt;
    public List<Lists> list;
    public City city;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public List<Lists> getList() {
		return list;
	}
	public void setList(List<Lists> list) {
		this.list = list;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
    
    
}
