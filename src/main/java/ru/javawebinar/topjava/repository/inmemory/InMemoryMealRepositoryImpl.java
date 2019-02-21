package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> this.save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.getUserId()==userId){
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                repository.put(meal.getId(), meal);
                return meal;
            }
            // treat case: update, but absent in storage
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        } else return null;

    }

    @Override
    public boolean delete(int id, int userId) {
        if (this.get(id, userId)==null) {
            return false;
        }
        return repository.remove(id)!=null;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal.getUserId()==userId) {
            return meal;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values()
                         .stream()
                         .filter(meal -> meal.getUserId()==userId)
                         .sorted((m1, m2) -> m2.getDate().compareTo(m1.getDate()))
                         .collect(Collectors.toList());
    }
}

