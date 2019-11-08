package ua.kiev.repairagency.domain.order;

import ua.kiev.repairagency.domain.user.User;

import java.time.LocalDate;

public class Response {
    private Long id;
    private String text;
    private User user;
    private LocalDate data;

    public Response(Long id, String text, User user, LocalDate data) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
