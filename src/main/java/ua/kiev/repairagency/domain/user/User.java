package ua.kiev.repairagency.domain.user;

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
}
