package com.marcuschok.UnifiedBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "chatroom_user",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;

    public ChatRoom() {
    }

    public ChatRoom(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", users=" + this.users +
                ", messages=" + this.messages +
                '}';
    }
}
