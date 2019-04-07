package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.AuditoriumType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class AuditoriumDaoImpl implements AuditoriumDao {

    private Map<AuditoriumType, Auditorium> auditoriums;

    public void setAuditoriums(Map<AuditoriumType, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(auditoriums.values());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Optional<Auditorium> value = auditoriums.values().stream().filter(auditorium -> auditorium.getName().equals(name)).findAny();
        return value.orElse(null);
    }
}
