package ru.job4j.passport.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.passport.model.Passport;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PassportRepository {
    AtomicInteger id = new AtomicInteger(4);
    private Map<Integer, Passport> passportsMap = new ConcurrentHashMap<>();
    {
        passportsMap.put(1, new Passport(1, 123456, LocalDate.now().plusDays(5)));
        passportsMap.put(2, new Passport(2, 987654, LocalDate.now().minusDays(2)));
        passportsMap.put(3, new Passport(3, 4444444, LocalDate.now().plusDays(80)));
    }

    public Passport create(Passport passport) {
        return passportsMap.put(this.id.incrementAndGet(), passport);
    }

    public boolean getByName(int serial) {
        return passportsMap.containsValue(serial);
    }

    public List<Passport> getAll() {
        return passportsMap.values().stream().toList();
    }

    public Passport update(int id, Passport passport) {
        return passportsMap.replace(id, passport);
    }

    public Passport delete(int id) {
        return passportsMap.remove(id);
    }

    public List<Passport> expired() {
        return passportsMap.values()
                .stream()
                .filter(passport -> passport.getExpired().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<Passport> expiredFor3Months() {
        return passportsMap.values()
                .stream()
                .filter(passport -> LocalDate.now().isAfter(passport.getExpired().minusDays(90)))
                .collect(Collectors.toList());
    }
}
