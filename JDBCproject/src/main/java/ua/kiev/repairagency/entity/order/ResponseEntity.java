package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.time.LocalDate;

public class ResponseEntity {
    private Long id;
    private String text;
    private UserEntity user;
    private LocalDate data;

    public ResponseEntity(Long id, String text, UserEntity user, LocalDate localDate) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.data = localDate;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public UserEntity getUser() {
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

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
