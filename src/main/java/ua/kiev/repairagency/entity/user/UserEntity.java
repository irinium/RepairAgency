package ua.kiev.repairagency.entity.user;

public class UserEntity {
    private final Long id;
    private final String password;
    private final String email;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final RoleEntity roleEntity;

    private UserEntity(Builder userBuilder) {
        this.id = userBuilder.id;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
        this.phoneNumber = userBuilder.phoneNumber;
        this.roleEntity = userBuilder.roleEntity;
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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        protected String password;
        private String email;
        private String name;
        private String surname;
        private RoleEntity roleEntity;
        private String phoneNumber;

        private Builder() {
        }

        public UserEntity build() {
            return new UserEntity(this);
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

        public Builder withRole(RoleEntity roleEntity) {
            this.roleEntity = roleEntity;
            return this;
        }
    }
}
