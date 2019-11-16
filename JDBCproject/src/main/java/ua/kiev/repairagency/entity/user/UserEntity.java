package ua.kiev.repairagency.entity.user;

public class UserEntity {
    private final Long id;
    private final String password;
    private final String email;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final RoleEntity roleEntity;

    private UserEntity(UserBuilder userUserBuilder) {
        this.id = userUserBuilder.id;
        this.password = userUserBuilder.password;
        this.email = userUserBuilder.email;
        this.name = userUserBuilder.name;
        this.surname = userUserBuilder.surname;
        this.phoneNumber = userUserBuilder.phoneNumber;
        this.roleEntity = userUserBuilder.roleEntity;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        protected String password;
        private String email;
        private String name;
        private String surname;
        private RoleEntity roleEntity;
        private String phoneNumber;

        private UserBuilder() {
        }

        public UserEntity build() {
            return new UserEntity(this);
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

        public UserBuilder withRole(RoleEntity roleEntity) {
            this.roleEntity = roleEntity;
            return this;
        }
    }
}
