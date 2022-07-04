package com.school.management.service.interfaces;

import com.school.management.model.person.Teacher;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IPersonService<T> {
    public T register(T person);

    public CompletableFuture<List<Teacher>> getAll();

    public void update(int id, T person);

    public void delete(int id);
}
