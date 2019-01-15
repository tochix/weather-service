package com.anselm.weather.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityTemperature {
	private Integer cityId;
	
	private String cityName;
	
	private String country;
	
	private Long timestamp;
	
	@JsonProperty("list")
	private List<TemperatureReading> tempReadings;
	
	public CityTemperature() {
		this.tempReadings = new ArrayList<>();
	}
	
	public Integer getCityId() {
		return cityId;
	}
	
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public List<TemperatureReading> getTempReadings() {
		return tempReadings;
	}

	public void setTempReadings(List<TemperatureReading> tempReadings) {
		this.tempReadings = tempReadings;
	}
	
    @JsonProperty("city")
    private void unpackNested(Map<String,Object> city) {
		this.country = (String) city.get("country");
        this.cityName = (String) city.get("name");
        this.cityId = (Integer) city.get("id");
    }
    
    @Override
    public String toString() {
        return "CityTemperature{" +
                "id=" + this.getCityId() +
                ", name='" + this.getCityName() + '\'' +
                ", country='" + this.getCountry() + '\'' +
                '}';
    }	
}
