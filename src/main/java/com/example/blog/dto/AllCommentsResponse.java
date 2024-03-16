package com.example.blog.dto;

import com.example.blog.domain.Article;
import com.example.blog.domain.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllCommentsResponse {
    private Long articleId;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private List<CommentResponse> comments;

}
