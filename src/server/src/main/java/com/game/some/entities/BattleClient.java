package com.game.some.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "battle_clients",
        uniqueConstraints = {@UniqueConstraint(name = "uniqueChain",
                columnNames = {"ip", "name"})})
public class BattleClient {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    Long id;

    @Column(nullable = false)
    String ip;

    @Column(nullable = false)
    String name;
}
