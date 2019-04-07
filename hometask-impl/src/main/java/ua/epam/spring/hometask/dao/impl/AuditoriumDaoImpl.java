package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class AuditoriumDaoImpl implements AuditoriumDao {

    @Autowired
    private Map<String, Auditorium> auditoriumMap;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return (Set<Auditorium>) auditoriumMap.values();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Optional<Auditorium> value = auditoriumMap.values().stream().filter(auditorium -> auditorium.getName().equals(name)).findAny();
        return value.orElse(null);
    }
}
