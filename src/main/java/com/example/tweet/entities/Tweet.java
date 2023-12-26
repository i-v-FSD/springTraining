package com.example.tweet.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private int id;

    @Column(name = "user_id", nullable = false)
    @Getter
    @Setter
    private int userId;

    @Column(name = "content", nullable = false, length = 140)
    @Getter
    @Setter
    private String content;

    @Column(name = "created_at", nullable = true)
    @Getter
    @Setter
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    @Getter
    @Setter
    private Date updatedAt;
}
