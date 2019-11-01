package ua.kiev.repairagency.entity.user;

import ua.kiev.repairagency.entity.Entity;

public class UserEntity extends Entity {
    private final Long id;
    private final String password;
    private final String email;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final RoleEntity roleEntity;

    protected UserEntity(UserBuilder<? extends UserBuilder> userUserBuilder) {
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

    public static class UserBuilder<SELF extends UserBuilder<SELF>> {
        private Long id;
        private String password;
        private String email;
        private String name;
        private String surname;
        private RoleEntity roleEntity;
        private String phoneNumber;

        public UserBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public UserEntity build() {
            return new UserEntity(self());
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

        public SELF withRole(RoleEntity roleEntity) {
            this.roleEntity = roleEntity;
            return self();
        }
    }
}
