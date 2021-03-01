package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="nijiDB")
public class Member{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="channel_name")
    private String channel_name;

    @Column(name="channel_id")
    private String channel_id;

    @Column(name="subscriber")
    private String subscriber;

    @Column(name="viewCount")
    private String viewCount;

    @Column(name="videoCount")
    private String videoCount;

    @Column(name="dateTime")
    private String dateTime;
}