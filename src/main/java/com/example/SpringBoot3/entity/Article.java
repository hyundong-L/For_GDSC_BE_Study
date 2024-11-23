package com.example.SpringBoot3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article articleEntity) {
        if (articleEntity.title != null) {
            this.title = articleEntity.title;
        }
        if (articleEntity.content != null) {
            this.content = articleEntity.content;
        }
    }
}
