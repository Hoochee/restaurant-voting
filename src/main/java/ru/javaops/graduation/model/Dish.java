package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Enabled;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "dishes_unique_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true,exclude = "menus")
public class Dish extends AbstractNamedEntity implements Serializable {

    @Column(name = "price",nullable = false)
    @NotNull
    @Range(min=1,max=5000)
    private Integer price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    @JsonBackReference
    private List<Menu> menus;

    public Dish(Integer id, String name, @NotNull @Range(min = 1, max = 5000) Integer price) {
        super(id, name);
        this.price = price;
    }
}
