package ru.job4j.passport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

@RestController
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Passport passport) {
        passportService.create(passport);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/find")
    public ResponseEntity<Void> getByName(@RequestParam int serial) {
        boolean status = passportService.getByName(serial);
        return ResponseEntity.status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/find/all")
    public List<Passport> getAll() {
        return passportService.getAll();
    }

    @GetMapping("/unavailible")
    public List<Passport> expired() {
        return passportService.expired();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> replaceable() {
        return passportService.expiredFor3Months();
    }

    @PutMapping
    public ResponseEntity<Passport> update(@RequestParam int id, @RequestBody Passport passport) {
        return ResponseEntity.ok().body(passportService.update(id, passport));
    }

    @DeleteMapping
    public ResponseEntity<Passport> delete(@RequestParam int id) {
        return ResponseEntity.ok(passportService.delete(id));
    }
}
