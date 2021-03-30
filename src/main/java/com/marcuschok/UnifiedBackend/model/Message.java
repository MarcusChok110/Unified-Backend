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

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatRoom chat;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String content;

    public Message() {
    }

    public Message(User author, String content) {
        this.author = author;
        this.content = content;
    }

    @PrePersist
    public void onCreate() {
        this.timestamp = new Date();
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

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + this.id +
                ", timestamp=" + this.timestamp +
                ", chat=" + this.chat +
                ", author=" + this.author +
                ", content='" + this.content + '\'' +
                '}';
    }
}
