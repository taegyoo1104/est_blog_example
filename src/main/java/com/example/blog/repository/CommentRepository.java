package com.example.blog.repository;

import com.example.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByArticleIdAndId(Long articleId, Long commentId);
}
