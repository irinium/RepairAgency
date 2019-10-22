package ua.kiev.repairagency.domain.appliance;

public class ApplianceEntity {
    private final Long id;
    private final String name;
    private final String model;
    private final double price;
    private final ManufacturerEntity manufacturerEntity;
    private final TypeEntity typeEntity;

    protected ApplianceEntity(ApplianceBuilder<? extends ApplianceBuilder> applianceBuilder) {
        this.id = applianceBuilder.id;
        this.name = applianceBuilder.name;
        this.model = applianceBuilder.model;
        this.price = applianceBuilder.price;
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

    public double getPrice() {
        return price;
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
        private double price;
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

        public SELF withPrice(double price) {
            this.price = price;
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
        return (id + " " + name + " - " + manufacturerEntity + " " + model + " - " + price + "\u20B4") ;
    }
}

