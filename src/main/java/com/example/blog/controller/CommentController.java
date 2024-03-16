package com.example.blog.controller;

import com.example.blog.domain.Comment;
import com.example.blog.dto.AddCommentRequest;
import com.example.blog.dto.AllCommentsResponse;
import com.example.blog.dto.CommentResponse;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;
    private final BlogService blogService;

    public CommentController(CommentService commentService, BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }

    @PostMapping("/comments/{articleId}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long articleId, @RequestBody AddCommentRequest request) {
        Comment comment = commentService.save(articleId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment.toResponse());
    }

    @GetMapping("/comments/{articleId}/{commentId}")
    public ResponseEntity<CommentResponse> showOneComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        Comment comment = commentService.getCommentByArticleIdAndCommentId(articleId, commentId);
        return ResponseEntity.ok(comment.toResponse());
    }

    @GetMapping("/comments/{articleId}")
    public ResponseEntity<AllCommentsResponse> showAllComments(@PathVariable Long articleId) {
        AllCommentsResponse allCommentsResponse = commentService.getArticleWithComments(articleId);
        return ResponseEntity.ok(allCommentsResponse);
    }


}
