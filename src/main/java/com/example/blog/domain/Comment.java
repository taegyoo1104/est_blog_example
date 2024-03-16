package com.example.blog.domain;

import com.example.blog.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "body", nullable = false)
    private String body;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Comment(Article article, String body) {
        this.article = article;
        this.body = body;
    }

    public CommentResponse toResponse() {
        return CommentResponse.builder()
                .id(id)
                .body(body)
                .created_at(createdAt)
                .build();
    }
}
