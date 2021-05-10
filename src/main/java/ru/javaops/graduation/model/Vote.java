package ru.javaops.graduation.model;

import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date", "user_id"}, name = "votes_unique_restaurant_date_user_idx")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vote extends AbstractBaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "date",nullable = false,columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate date;

}
