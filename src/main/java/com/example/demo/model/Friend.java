package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_friend")
@Entity
public class Friend {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @ManyToOne
    private User myself;
    @ManyToOne
    private User friend;

}
