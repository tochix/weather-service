package com.anselm.weather.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureReading {
	
	@JsonProperty("dt")
	private Long timestamp;
	
	private Double temperature;
	
	private Double minTemperature;
	
	private Double maxTemperature;
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Date getDate() {
		Timestamp timestamp = new Timestamp(this.getTimestamp() * 1000);
		Date date = new Date(timestamp.getTime());
		
		return date;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Double minTemperature) {
		this.minTemperature = minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	
	@JsonProperty("main")
    private void unpackNested(Map<String,Double> main) {
		this.temperature = main.get("temp");
        this.minTemperature = main.get("temp_min");
        this.maxTemperature = main.get("temp_max");
    }
	
	@Override
    public String toString() {
        return "TemperatureReading{" +
                "temp=" + this.getTemperature() +
                ", minTemp='" + this.getMinTemperature() + '\'' +
                ", maxTemp='" + this.getMaxTemperature() + '\'' +
                ", timestamp='" + this.getDate() + '\'' +
                '}';
    }
}
