package ua.kiev.repairagency.entity.appliance;

import ua.kiev.repairagency.entity.Entity;

public class ApplianceEntity extends Entity {
    private final Long id;
    private final String name;
    private final String model;
    private final String disrepair;
    private final ManufacturerEntity manufacturerEntity;
    private final TypeEntity typeEntity;

    protected ApplianceEntity(ApplianceBuilder<? extends ApplianceBuilder> applianceBuilder) {
        this.id = applianceBuilder.id;
        this.name = applianceBuilder.name;
        this.model = applianceBuilder.model;
        this.disrepair = applianceBuilder.disrepair;
        this.manufacturerEntity = applianceBuilder.manufacturerEntity;
        this.typeEntity = applianceBuilder.typeEntity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getDisrepair() {
        return disrepair;
    }

    public ManufacturerEntity getManufacturerEntity() {
        return manufacturerEntity;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }



    public static class ApplianceBuilder<SELF extends ApplianceBuilder<SELF>> {
        private Long id;
        private String name;
        private String model;
        private String disrepair;
        private ManufacturerEntity manufacturerEntity;
        private TypeEntity typeEntity;

        protected ApplianceBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public ApplianceEntity build() {
            return new ApplianceEntity(self());
        }

        public SELF withId(Long id) {
            this.id = id;
            return self();
        }

        public SELF withName(String name) {
            this.name = name;
            return self();
        }

        public SELF withModel(String model) {
            this.model = model;
            return self();
        }

        public SELF withDisrepair(String disrepair) {
            this.disrepair = disrepair;
            return self();
        }

        public SELF withManufacturer(ManufacturerEntity manufacturerEntity) {
            this.manufacturerEntity = manufacturerEntity;
            return self();
        }

        public SELF withType(TypeEntity typeEntity) {
            this.typeEntity = typeEntity;
            return self();
        }
    }

    @Override
    public String toString() {
        return (id + " " + name + " - " + manufacturerEntity + " " + model + " - " + disrepair) ;
    }
}

