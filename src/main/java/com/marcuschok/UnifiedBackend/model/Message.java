package com.marcuschok.UnifiedBackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private String authorId;

    private String author;

    private String content;

    public Message() {
    }

    public Message(String authorId, String author, String content) {
        this.authorId = authorId;
        this.author = author;
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @PrePersist
    public void onCreate() {
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + this.id +
                ", timestamp=" + this.timestamp +
                ", authorId='" + this.authorId + '\'' +
                ", author='" + this.author + '\'' +
                ", content='" + this.content + '\'' +
                '}';
    }
}
