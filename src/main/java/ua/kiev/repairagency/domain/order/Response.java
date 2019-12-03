package ua.kiev.repairagency.domain.order;

import ua.kiev.repairagency.domain.user.User;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(getId(), response.getId()) &&
                Objects.equals(getText(), response.getText()) &&
                Objects.equals(getUser(), response.getUser());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = (int) (prime * result + (getId() == null ? 0 : getId()));
        return result;
    }
}
