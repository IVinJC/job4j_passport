package ru.job4j.passport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.repository.PassportRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassportService {
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final PassportRepository passportRepository;

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
    @Scheduled(fixedDelay = 10000)
    public List<Passport> expired() {
        Optional<List<Passport>> expired = Optional.of(passportRepository.expired());
        if (expired.isPresent()) {
            kafkaTemplate.send("expired", expired.get().toString());
        }
        return expired.orElse(null);
    }

    public List<Passport> expiredFor3Months() {
        return passportRepository.expiredFor3Months();
    }
}
