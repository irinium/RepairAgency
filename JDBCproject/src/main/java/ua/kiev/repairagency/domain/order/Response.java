package ua.kiev.repairagency.domain.order;

import ua.kiev.repairagency.domain.user.User;

public class Response {
    private Long id;
    private String text;
    private User user;

    public Response(String text, User user) {
        this.text = text;
        this.user = user;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
