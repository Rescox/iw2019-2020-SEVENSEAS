package es.uca.iw.sss.spring.meteorology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by moksha on 03/07/2016.
 */
@Service
    public class WeatherService implements WeatherInterface{

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=";

    private final RestTemplate restTemplate;

    private final String apiKey = "&appid=b3e0dd71a4da8ad6f06b335010a2b8ca";


    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("weather")
    public Weather getWeather(String city){

        Weather weather = null;
        //if(validParameters(city)) {
            //URI url = new UriTemplate(this.apiUrl).expand(city, this.apiKey);
            String apiUrlModified = apiUrl + city + apiKey;
            URI url = new UriTemplate(apiUrlModified).expand();
            weather = invoke(url, Weather.class);
        //}
        return weather;
    }

    private boolean validParameters(String city) {
        return city !=null && !"".equals(city) && apiKey !=null && !"".equals(apiKey) && apiUrl!=null && !"".equals(apiUrl);
    }

    private <T> T invoke(URI url, Class<T> responseType){
        T weather = null;
        try {
            RequestEntity<?> request = RequestEntity.get(url)
                    .accept(MediaType.APPLICATION_JSON).build();
            ResponseEntity<T> exchange = this.restTemplate
                    .exchange(request, responseType);
            weather = exchange.getBody();
        } catch(Exception e){
            logger.error("An error occurred while calling openweathermap.org API endpoint:  " + e.getMessage());
        }

        return weather;
    }


}