package ua.kiev.repairagency.entity.user;

public class MasterEntity extends UserEntity {

    protected MasterEntity(MasterBuilder masterBuilder) {
        super(masterBuilder);
    }

    public static class MasterBuilder extends UserBuilder<MasterBuilder> {

        public MasterBuilder() {
        }

        @Override
        public MasterBuilder self() {
            return this;
        }

        public MasterEntity build() {
            return new MasterEntity(self());
        }
    }
}
