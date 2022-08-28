package ru.job4j.passport.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passport.model.Passport;

import java.util.List;

public class PassportAPI {
    @Value("${api-url}")
    private String url;
    private final RestTemplate restTemplate;

    public PassportAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Passport add(Passport passport) {
        return restTemplate.postForEntity(url, passport, Passport.class).getBody();
    }

    public Passport getBySerial(int serial) {
        return restTemplate.getForObject(String.format("%s/find?serial=%s", url, serial), Passport.class);
    }

    public List<Passport> getAll() {
        return restTemplate.exchange(
                String.format("%s/find/all", url), HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
    }

    public Passport update(int id, Passport passport) {
        return restTemplate.postForEntity(String.format("%s?id=%s", url, id), passport, Passport.class).getBody();
    }

    public Passport delete(int id) {
        return restTemplate.exchange(
                String.format("%s?id=%s", url, id), HttpMethod.DELETE, HttpEntity.EMPTY, Passport.class)
                .getBody();
    }

    public List<Passport> expired() {
        return restTemplate.exchange(
                String.format("%s/unavailible", url), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }

    public List<Passport> expiredFor3Months() {
        return restTemplate.exchange(
                String.format("%s/find-replaceable", url), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }
}
