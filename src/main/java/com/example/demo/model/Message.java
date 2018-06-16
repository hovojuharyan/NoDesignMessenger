package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
@Entity
public class Message {
    @Id
    @Column
    @GeneratedValue
    private int id;
    @Column
    private String text;
    @ManyToOne
    private User source;
    @ManyToOne
    private User destination;
    @Column(name = "time_stamp")
    private String timeStamp;
}
