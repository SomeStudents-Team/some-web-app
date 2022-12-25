package com.game.some.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "battles")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String description;

    @OneToOne
    @JoinColumn(name = "lead")
    BattleMember leader;

    @Column(nullable = false, columnDefinition = "2")
    Integer status;

    @Column(name = "time_start", nullable = false)
    LocalDateTime startAt;

    @OneToOne
    @JoinColumn(nullable = false)
    BattleClient initiator;

    @OneToMany(targetEntity = BattleMember.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "battle")
    List<BattleMember> members = new ArrayList<>();
}
