package ua.kiev.repairagency.domain.appliance;

public class ElectricApplianceEntity extends ApplianceEntity {
    private final int powerConsumption;

    protected ElectricApplianceEntity(ElectricApplianceBuilder builder) {
        super(builder);
        this.powerConsumption = builder.powerConsumption;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public static class ElectricApplianceBuilder extends ApplianceBuilder<ElectricApplianceBuilder> {
        private int powerConsumption;

        public ElectricApplianceBuilder() {
        }

        @Override
        public ElectricApplianceBuilder self() {
            return this;
        }

        public ElectricApplianceEntity build() {
            return new ElectricApplianceEntity(self());
        }

        public ElectricApplianceBuilder withPowerConsumption(int powerConsumption) {
            this.powerConsumption = powerConsumption;
            return self();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
