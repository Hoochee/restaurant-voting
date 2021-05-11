package ru.javaops.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants",uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurant_unique_name_idx")})
@Getter
@Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true,  exclude = {"menus", "votes"})
public class Restaurant extends AbstractNamedEntity implements Serializable {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<Menu> menus;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Vote> votes;
}
