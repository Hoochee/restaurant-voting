package ru.javaops.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.reppository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.URL)
@AllArgsConstructor
@Slf4j
public class AdminRestaurantController {
    static final String URL = "api/admin/restaurants";
    private final RestaurantRepository restaurantRepository;

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@CacheEvict(value = "users", key = "#authUser.username")
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        restaurantRepository.deleteById(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get restaurant {} ", id);
        return ResponseEntity.of(restaurantRepository.findById(id));
    }

}
