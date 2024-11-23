package com.example.SpringBoot3.dto;


import com.example.SpringBoot3.entity.Article;
import lombok.*;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;   //제목을 받을 필드
    private String content; //내용을 받을 필드

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
