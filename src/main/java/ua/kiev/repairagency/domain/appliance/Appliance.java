package ua.kiev.repairagency.domain.appliance;

import ua.kiev.repairagency.domain.user.User;

public class Appliance {
    private final Long id;
    private final String name;
    private final String model;
    private final String disrepair;
    private final Manufacturer manufacturer;
    private final Type type;
    private final User user;

    private Appliance(Builder applianceBuilder) {
        this.id = applianceBuilder.id;
        this.name = applianceBuilder.name;
        this.model = applianceBuilder.model;
        this.disrepair = applianceBuilder.disrepair;
        this.manufacturer = applianceBuilder.manufacturer;
        this.type = applianceBuilder.type;
        this.user = applianceBuilder.user;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Type getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String model;
        private String disrepair;
        private Manufacturer manufacturer;
        private Type type;
        private User user;

        private Builder() {
        }

        public Appliance build() {
            return new Appliance(this);
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

        public Builder withManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder withType(Type type) {
            this.type = type;
            return this;
        }
        public Builder withUser(User user) {
            this.user = user;
            return this;
        }
    }
}
