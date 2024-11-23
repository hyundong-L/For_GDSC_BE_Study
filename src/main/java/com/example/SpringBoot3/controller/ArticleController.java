package com.example.SpringBoot3.controller;

import com.example.SpringBoot3.dto.ArticleForm;
import com.example.SpringBoot3.dto.CommentDto;
import com.example.SpringBoot3.entity.Article;
import com.example.SpringBoot3.repository.ArticleRepository;
import com.example.SpringBoot3.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());    //데이터가 잘 받아졌는지 확인용

        //1. DTO -> Entity
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        //2. Repository -> DB
        Article saved = articleRepository.save(article);
//        System.out.println(saved);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        //1. id 조회, 가져온 데이터를 엔티티에 저장
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);

        //2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1. 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();

        //2. 모델에 데이터 등록
        model.addAttribute("articleList", articleEntityList);

        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //1. DB에서 수정 데이터 찾아오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        //1. DTO -> Entity
        Article articleEntity = form.toEntity();

        //2. Entity -> DB
        //2-1. 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2. 기존 데이터 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        //3. Redirect
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);

        //2. 대상 엔티티 삭제
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제했습니다.");
        }

        //3. 결과 페이지로 리다이렉트

        return "redirect:/articles";
    }
}
