package com.game.some.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "battle_clients",
        uniqueConstraints = {@UniqueConstraint(name = "uniqueChain",
                columnNames = {"ip", "name"})})
public class BattleClient {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String ip;

    @Column(nullable = false)
    String name;
}
