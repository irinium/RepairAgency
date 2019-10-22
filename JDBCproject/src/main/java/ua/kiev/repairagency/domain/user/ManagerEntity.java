package ua.kiev.repairagency.domain.user;

public class ManagerEntity extends UserEntity {

    private ManagerEntity(ManagerBuilder managerBuilder) {
        super(managerBuilder);
    }

    public static class ManagerBuilder extends UserBuilder<ManagerBuilder> {

        public ManagerBuilder() {
        }

        @Override
        public ManagerBuilder self() {
            return this;
        }

        public ManagerEntity build() {
            return new ManagerEntity(self());
        }
    }
}
