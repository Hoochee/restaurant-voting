package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Enabled;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dishes",
        //uniqueConstraints = {@UniqueConstraint(columnNames = {"on_date"}, name = "menus_unique_restaurant_on_date_idx")},
        indexes = {@Index(columnList = "on_date", name = "dish_on_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true,exclude = "menus")
public class Dish extends AbstractNamedEntity implements Serializable {

    @Column(name = "price",nullable = false)
    @NotNull
    @Range(min=1,max=5000)
    private Integer price;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    @JsonBackReference
    private List<Menu> menus;*/

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "on_date", nullable = false)
    @NotNull
    private LocalDate date;


}
