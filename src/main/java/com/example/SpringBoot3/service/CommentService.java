package com.example.SpringBoot3.service;

import com.example.SpringBoot3.dto.CommentDto;
import com.example.SpringBoot3.entity.Article;
import com.example.SpringBoot3.entity.Comment;
import com.example.SpringBoot3.repository.ArticleRepository;
import com.example.SpringBoot3.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository
                .findByArticleId(articleId)
                .stream().map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //1. 게시글 조회, 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " + "대상 게시글이 없습니다."));

        //2. Entity
        Comment comment = Comment.createComment(dto, article);

        //3. Entity -> DB
        Comment created = commentRepository.save(comment);

        //4. Entity -> DTO, return
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //1. 댓글 조회, 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! " + "대상 댓글이 없습니다."));

        //2. 댓글 수정
        target.patch(dto);

        //3. DB로 갱신
        Comment updated = commentRepository.save(target);

        //4. 댓글 entity -> DTO, return
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        //1. 게시글 조회, 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! " + "대상이 없습니다."));

        //2. 댓글 삭제
        commentRepository.delete(target);

        //3. 삭제 댓글을 dto로 변환해 반환
        return CommentDto.createCommentDto(target);
    }
}
