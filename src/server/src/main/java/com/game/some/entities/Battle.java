package com.game.some.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "battles")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String description;

    @OneToOne
    @JoinColumn(name = "leader")
    BattleMember leader;

    @Column(nullable = false)
    Integer state;

    @Column(name = "time_start", nullable = false)
    LocalDate dateStart;

    @OneToOne
    @JoinColumn(nullable = false)
    BattleClient owner;

    @OneToMany(targetEntity = BattleMember.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "battle")
    List<BattleMember> members = new ArrayList<>();
}
