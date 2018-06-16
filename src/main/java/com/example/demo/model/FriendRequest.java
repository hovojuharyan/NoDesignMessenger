package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_request")
@Entity
public class FriendRequest {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @ManyToOne
    private User source;
    @ManyToOne
    private User destination;

}
