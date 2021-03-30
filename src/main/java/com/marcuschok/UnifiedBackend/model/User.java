package com.marcuschok.UnifiedBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "author")
    private Set<Message> messages;

    @ManyToMany(mappedBy = "users")
    private Set<ChatRoom> chats;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<ChatRoom> getChats() {
        return this.chats;
    }

    public void setChats(Set<ChatRoom> chats) {
        this.chats = chats;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", messages=" + this.messages +
                ", chats=" + this.chats +
                '}';
    }
}
