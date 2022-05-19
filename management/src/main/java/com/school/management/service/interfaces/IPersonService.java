package com.school.management.service.interfaces;

import java.util.List;

public interface IPersonService<T> {
    public T register(T person);

    public List<T> getAll();

    public void update(int id, T person);

    public void delete(int id);
}
