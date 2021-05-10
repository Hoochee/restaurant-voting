package ru.javaops.graduation.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Override
    @Modifying
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    @Modifying
    @Transactional
    void deleteById(Integer integer);

    @Override
    @Modifying
    @Transactional
    void delete(Restaurant restaurant);

   /* @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> get(int id);*/




}
