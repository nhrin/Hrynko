package com.example.todoapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Boolean completed = false;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser user;
}
