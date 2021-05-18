package ru.javaops.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.reppository.AdminRepository;
import ru.javaops.graduation.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Slf4j
@RequestMapping(value = AdminController.URL)
@AllArgsConstructor
public class AdminController implements RepresentationModelProcessor<RepositoryLinksResource> {

    @SuppressWarnings("unchecked")
    private static final RepresentationModelAssemblerSupport<Restaurant, EntityModel<Restaurant>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(AdminController.class, (Class<EntityModel<Restaurant>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Restaurant> toModel(Restaurant restaurant) {
                    return EntityModel.of(restaurant, linkTo(AdminController.class).withSelfRel());
                }
            };
    static final String URL = "rest/admin/restaurants";
    private final AdminRepository repository;

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public Restaurant update(@Valid @RequestBody Restaurant restaurant) {
        log.info("update {} ",restaurant);
        Restaurant oldRestaurant = repository.getOne(restaurant.getId());
        ValidationUtil.assureIdConsistent(restaurant, oldRestaurant.id());

        return repository.save(restaurant);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        //restaurant.setDishes();
        Restaurant created = repository.save(restaurant);


        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL)
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
       repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get restaurant {} ", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @GetMapping("/{id}/menu")
    public List<Dish> getRestaurantMenu(@PathVariable int id) {
        log.info("get restaurant {} with menus", id);
        return repository.getMenu(id);
    }


    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(linkTo(AdminController.class).withRel("admin"));
        return resource;
    }


}
