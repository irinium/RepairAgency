package ua.kiev.repairagency.domain.user;

public class User {
    private final Long id;
    protected String password;
    private final String email;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final Role role;

    protected User(UserBuilder<? extends UserBuilder> userUserBuilder) {
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

    public static class UserBuilder<SELF extends UserBuilder<SELF>> {
        private Long id;
        private String password;
        private String email;
        private String name;
        private String surname;
        private Role role;
        private String phoneNumber;

        public UserBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public User build() {
            return new User(self());
        }

        public SELF withId(Long id) {
            this.id = id;
            return self();
        }

        public SELF withPassword(String password) {
            this.password = password;
            return self();
        }

        public SELF withEmail(String email) {
            this.email = email;
            return self();
        }

        public SELF withName(String name) {
            this.name = name;
            return self();
        }

        public SELF withSurname(String surname) {
            this.surname = surname;
            return self();
        }

        public SELF withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return self();
        }

        public SELF withRole(Role role) {
            this.role = role;
            return self();
        }
    }
}
