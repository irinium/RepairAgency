package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.entity.user.UserEntity;

public class ResponseEntity {
    private Long id;
    private String text;
    private UserEntity user;

    public ResponseEntity( String text, UserEntity user) {
        this.text = text;
        this.user = user;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
