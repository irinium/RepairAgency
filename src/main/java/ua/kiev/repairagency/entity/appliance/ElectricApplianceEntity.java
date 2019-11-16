package ua.kiev.repairagency.entity.appliance;

import ua.kiev.repairagency.entity.user.UserEntity;

public class ElectricApplianceEntity{
    private final Long id;
    private final String name;
    private final String model;
    private final String disrepair;
    private final ManufacturerEntity manufacturer;
    private final TypeEntity type;
    private final UserEntity userEntity;

    private ElectricApplianceEntity(ElectricApplianceBuilder applianceBuilder) {
        this.id = applianceBuilder.id;
        this.name = applianceBuilder.name;
        this.model = applianceBuilder.model;
        this.disrepair = applianceBuilder.disrepair;
        this.manufacturer = applianceBuilder.manufacturer;
        this.type = applianceBuilder.type;
        this.userEntity = applianceBuilder.userEntity;
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
        return manufacturer;
    }

    public TypeEntity getTypeEntity() {
        return type;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public static ElectricApplianceBuilder builder(){
        return new ElectricApplianceBuilder();
    }

    public static class ElectricApplianceBuilder {
        private Long id;
        private String name;
        private String model;
        private String disrepair;
        private ManufacturerEntity manufacturer;
        private TypeEntity type;
        private UserEntity userEntity;

        private ElectricApplianceBuilder() {
        }

        public ElectricApplianceEntity build() {
            return new ElectricApplianceEntity(this);
        }

        public ElectricApplianceBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ElectricApplianceBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ElectricApplianceBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public ElectricApplianceBuilder withDisrepair(String disrepair) {
            this.disrepair = disrepair;
            return this;
        }

        public ElectricApplianceBuilder withManufacturer(ManufacturerEntity manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public ElectricApplianceBuilder withType(TypeEntity type) {
            this.type = type;
            return this;
        }

        public ElectricApplianceBuilder withUser(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

    }
}