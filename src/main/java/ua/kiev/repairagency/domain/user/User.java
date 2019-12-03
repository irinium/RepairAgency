package ua.kiev.repairagency.domain.user;

import java.util.Objects;

public class User {
    private final Long id;
    protected String password;
    private final String email;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final Role role;

    private User(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.email = builder.email;
        this.surname = builder.surname;
        this.name = builder.name;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String password;
        private String email;
        private String name;
        private String surname;
        private Role role;
        private String phoneNumber;

        private Builder() {
        }

        public User build() {
            return new User(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSurname() == null) ? 0 : getSurname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = (int) (prime * result + (getId()==null ? 0: getId()));
        return result;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + surname + " \n email: " + email + " phone: " + phoneNumber;
    }
}
