package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.domain.Comment;
import com.example.blog.dto.AddCommentRequest;
import com.example.blog.dto.AllCommentsResponse;
import com.example.blog.dto.ArticleResponse;
import com.example.blog.dto.CommentResponse;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public CommentService(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    public Comment save(Long articleId, AddCommentRequest request) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return commentRepository.save(request.toEntity(article));
    }

    public Comment getCommentByArticleIdAndCommentId(Long articleId, Long commentId) {
        return commentRepository.findByArticleIdAndId(articleId, commentId);
    }


    public AllCommentsResponse getArticleWithComments(Long articleId) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        /*
        List<CommentResponse> commentResponses = article.getComments().stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getBody(), comment.getCreatedAt()))
                .collect(Collectors.toList());

         */
        List<CommentResponse> commentResponses = article.getComments().stream()
                .map(CommentResponse::new)
                .toList();

        return new AllCommentsResponse(article.getId(), article.getTitle(), article.getContent(),
                article.getCreatedAt().toString(), article.getUpdatedAt().toString(), commentResponses);

    }
}
