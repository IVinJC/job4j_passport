package ru.job4j.passport.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Passport {
    @NonNull
    private int id;
    @NonNull
    private int serialNumber;
    @NonNull
    private LocalDate expired;
}
