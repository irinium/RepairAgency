package ua.kiev.repairagency.domain.user;

public class Manager extends User {

    protected Manager(ManagerBuilder managerBuilder) {
        super(managerBuilder);
    }

    public static class ManagerBuilder extends UserBuilder<ManagerBuilder> {

        public ManagerBuilder() {
        }

        @Override
        public ManagerBuilder self() {
            return this;
        }

        public Manager build() {
            return new Manager(self());
        }
    }
}
