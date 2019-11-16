package ua.kiev.repairagency.domain.user;

public class User {
    private final Long id;
    protected String password;
    private final String email;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final Role role;

    private User(UserBuilder userUserBuilder) {
        this.id = userUserBuilder.id;
        this.password = userUserBuilder.password;
        this.email = userUserBuilder.email;
        this.surname = userUserBuilder.surname;
        this.name = userUserBuilder.name;
        this.phoneNumber = userUserBuilder.phoneNumber;
        this.role = userUserBuilder.role;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String password;
        private String email;
        private String name;
        private String surname;
        private Role role;
        private String phoneNumber;

        private UserBuilder() {
        }

        public User build() {
            return new User(this);
        }

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }
    }
}
