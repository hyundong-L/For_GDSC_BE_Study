package com.example.SpringBoot3.service;

import com.example.SpringBoot3.dto.ArticleForm;
import com.example.SpringBoot3.entity.Article;
import com.example.SpringBoot3.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article articleEntity = dto.toEntity();

        if (articleEntity.getId() != null) {
            return null;
        }

        return articleRepository.save(articleEntity);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. DTO -> Entity
        Article articleEntity = dto.toEntity();

        //2. find data
        Article target = articleRepository.findById(id).orElse(null);

        //3. Invalid requests
        if (target == null || id != articleEntity.getId()) {
            log.info("잘못된 요청");
            return null;
        }

        //4. update
        target.patch(articleEntity);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //1. find Entity in DB
        Article target = articleRepository.findById(id).orElse(null);

        //2. Invalid requests
        if (target == null || id != target.getId()) {
            return null;
        }

        //3. return
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. dto -> Entity
        List<Article> articleList=dtos.stream().map(ArticleForm::toEntity).toList();

        //2. Entity -> DB
        articleRepository.saveAll(articleList);

        //3. Raising Exception
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패"));

        //4. return
        return articleList;
    }
}
