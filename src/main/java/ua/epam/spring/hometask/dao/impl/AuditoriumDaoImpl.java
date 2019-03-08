package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class AuditoriumDaoImpl implements AuditoriumDao {

    private Set<Auditorium> auditoriums;

    public void setAuditoriums(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriums.stream().filter(auditorium -> auditorium.getName().equals(name)).findAny().get();
    }
}
