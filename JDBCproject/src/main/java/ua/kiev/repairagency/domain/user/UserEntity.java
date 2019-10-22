package ua.kiev.repairagency.domain.user;

public class UserEntity {
    private final Long id;
    private final String password;
    private final String email;
    private final RoleEntity roleEntity;

    protected UserEntity(UserBuilder<? extends UserBuilder> userUserBuilder) {
        this.id = userUserBuilder.id;
        this.password = userUserBuilder.password;
        this.email = userUserBuilder.email;
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

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public static class UserBuilder<SELF extends UserBuilder<SELF>> {
        private Long id;
        private String password;
        private String email;
        private RoleEntity roleEntity;

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

        public SELF withRole(RoleEntity roleEntity) {
            this.roleEntity = roleEntity;
            return self();
        }
    }
}
