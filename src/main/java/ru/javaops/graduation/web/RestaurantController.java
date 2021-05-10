package ru.javaops.graduation.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.graduation.AuthUser;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.reppository.RestaurantRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = RestaurantController.URL)
@AllArgsConstructor
@Slf4j
public class RestaurantController /*implements RepresentationModelProcessor<RepositoryLinksResource>*/ {
    static final String URL = "api/restaurants";

  /*  @SuppressWarnings("unchecked")
    private static final RepresentationModelAssemblerSupport<Restaurant, EntityModel<Restaurant>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(RestaurantController.class, (Class<EntityModel<Restaurant>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Restaurant> toModel(Restaurant restaurant) {
                    return EntityModel.of(restaurant, linkTo(RestaurantController.class).withSelfRel());
                }
            };*/

    private final RestaurantRepository restaurantRepository;

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


    /*@GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Restaurant> get(@RequestBody Restaurant restaurant) {
        log.info("get {}", restaurant);
        return ASSEMBLER.toModel(restaurantRepository.getOne(restaurant.getId()));
    }*/
   /* @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@CacheEvict(value = "users", key = "#authUser.username")
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        restaurantRepository.deleteById(id);
    }*/

    /*@Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(linkTo(RestaurantController.class).withRel("restaurants"));
        return resource;



}*/

}
