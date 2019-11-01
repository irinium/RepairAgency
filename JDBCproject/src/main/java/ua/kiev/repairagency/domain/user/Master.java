package ua.kiev.repairagency.domain.user;

public class Master extends User {

    protected Master(MasterBuilder masterBuilder) {
        super(masterBuilder);
    }

    public static class MasterBuilder extends UserBuilder<MasterBuilder> {

        public MasterBuilder() {
        }

        @Override
        public MasterBuilder self() {
            return this;
        }

        public Master build() {
            return new Master(self());
        }
    }
}
