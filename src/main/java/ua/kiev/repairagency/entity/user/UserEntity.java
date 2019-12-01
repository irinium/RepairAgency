package ua.kiev.repairagency.entity.user;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(phoneNumber, user.getPhoneNumber()) &&
                getRoleEntity() == user.getRoleEntity();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSurname() == null) ? 0 : getSurname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getRoleEntity() == null) ? 0 : getRoleEntity().hashCode());
        result = (int) (prime * result + (getId()==null ? 0: getId()));
        return result;
    }
}
