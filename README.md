# weather-service
Fetch weather forecast for cities in configured intervals from https://openweathermap.org/forecast5

Requirements
------------
- Java SE 1.8 or newer
- Maven
- API Key from [Open Weather Map][owm] 

Install
-------

To install clone this repo :

```sh
git clone git@github.com:tochix/weather-service.git
cd weather-service
```

Usage
-----
- Insert you API Key [here][api_key]
- Be mindful of rate limiting, they have it at 60 calls per minute
- Don't set the polling frquency less than that
- Run the app using maven from your CLI

```sh
cd weather-service
./mvnw spring-boot:run
```

[owm]: https://openweathermap.org/appid
[api_key]: https://github.com/tochix/weather-service/blob/master/src/main/resources/application.properties#L4
