package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseEntity response = (ResponseEntity) o;
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
        result = (int) (prime * result + getId());
        return result;
    }
}
