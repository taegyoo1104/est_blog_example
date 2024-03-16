package com.example.blog.dto;

import com.example.blog.domain.Article;
import com.example.blog.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentRequest {
    private String body;

    public Comment toEntity(Article article) {
        return Comment.builder()
                .body(body)
                .article(article)
                .build();
    }
}
