package ru.javaops.graduation.reppository;

import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface AdminRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:id")
    List<Dish> getMenu(int id);




}
