package com.school.management.service.interfaces;

import java.util.List;

public interface IPersonService<T> {
    public T register(T person);

    public List<T> getAll();

    public void update(long id, T Person);

    public void delete(long id);
}
