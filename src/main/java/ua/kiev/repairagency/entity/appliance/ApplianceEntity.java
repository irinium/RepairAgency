package ua.kiev.repairagency.entity.appliance;

import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.Objects;

public class ApplianceEntity {
    private final Long id;
    private final String name;
    private final String model;
    private final String disrepair;
    private final ManufacturerEntity manufacturer;
    private final TypeEntity type;
    private final UserEntity userEntity;

    private ApplianceEntity(Builder applianceBuilder) {
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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String model;
        private String disrepair;
        private ManufacturerEntity manufacturer;
        private TypeEntity type;
        private UserEntity userEntity;

        private Builder() {
        }

        public ApplianceEntity build() {
            return new ApplianceEntity(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withDisrepair(String disrepair) {
            this.disrepair = disrepair;
            return this;
        }

        public Builder withManufacturer(ManufacturerEntity manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder withType(TypeEntity type) {
            this.type = type;
            return this;
        }

        public Builder withUser(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        ApplianceEntity appliance = (ApplianceEntity) o;
        return Objects.equals(getId(), appliance.getId()) &&
                Objects.equals(getName(), appliance.getName()) &&
                Objects.equals(getModel(), appliance.getModel()) &&
                Objects.equals(getDisrepair(), appliance.getDisrepair()) &&
                getManufacturerEntity() == appliance.getManufacturerEntity() &&
                getTypeEntity() == appliance.getTypeEntity() &&
                getUserEntity().equals(appliance.getUserEntity());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getManufacturerEntity() == null) ? 0 : getManufacturerEntity().hashCode());
        result = prime * result + ((getDisrepair() == null) ? 0 : getDisrepair().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTypeEntity() == null) ? 0 : getTypeEntity().hashCode());
        result = prime * result + ((getUserEntity() == null) ? 0 : getUserEntity().hashCode());
        result = (int) (prime * result + (getId()==null ? 0 : getId()));
        return result;
    }
}