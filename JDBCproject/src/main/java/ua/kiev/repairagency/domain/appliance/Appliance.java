package ua.kiev.repairagency.domain.appliance;

public class Appliance {
    private final Long id;
    private final String name;
    private final String model;
    private final String disrepair;
    private final Manufacturer manufacturer;
    private final Type type;

    protected Appliance(ApplianceBuilder<? extends ApplianceBuilder> applianceBuilder) {
        this.id = applianceBuilder.id;
        this.name = applianceBuilder.name;
        this.model = applianceBuilder.model;
        this.disrepair = applianceBuilder.disrepair;
        this.manufacturer = applianceBuilder.manufacturer;
        this.type = applianceBuilder.type;
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



    public static class ApplianceBuilder<SELF extends ApplianceBuilder<SELF>> {
        private Long id;
        private String name;
        private String model;
        private String disrepair;
        private Manufacturer manufacturer;
        private Type type;

        protected ApplianceBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public Appliance build() {
            return new Appliance(self());
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

        public SELF withManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
            return self();
        }

        public SELF withType(Type type) {
            this.type = type;
            return self();
        }
    }

    @Override
    public String toString() {
        return (id + " " + name + " - " + manufacturer + " " + model + " - " + disrepair) ;
    }
}

