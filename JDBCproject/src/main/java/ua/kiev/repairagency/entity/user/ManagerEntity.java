package ua.kiev.repairagency.entity.user;

public class ManagerEntity extends UserEntity {

    protected ManagerEntity(ManagerBuilder managerBuilder) {
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
