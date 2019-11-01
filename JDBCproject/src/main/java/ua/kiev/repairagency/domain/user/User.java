package ua.kiev.repairagency.domain.user;

public class User {
    private final Long id;
    private final String password;
    private final String email;
    private final String phoneNumber;
    private final Role role;

    protected User(UserBuilder<? extends UserBuilder> userUserBuilder) {
        this.id = userUserBuilder.id;
        this.password = userUserBuilder.password;
        this.email = userUserBuilder.email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public static class UserBuilder<SELF extends UserBuilder<SELF>> {
        private Long id;
        private String password;
        private String email;
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
