package ru.job4j.passport.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.repository.PassportRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Passport create(Passport passport) {
        return passportRepository.create(passport);
    }

    public boolean getByName(int serial) {
        return passportRepository.getByName(serial);
    }

    public List<Passport> getAll() {
        return passportRepository.getAll();
    }

    public Passport update(int id, Passport passport) {
        return passportRepository.update(id, passport);
    }

    public Passport delete(int id) {
        return passportRepository.delete(id);
    }

    public List<Passport> expired() {
        return passportRepository.expired();
    }

    public List<Passport> expiredFor3Months() {
        return passportRepository.expiredFor3Months();
    }
}
