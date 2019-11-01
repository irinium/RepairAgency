package ua.kiev.repairagency.domain.appliance;

public class ElectricAppliance extends Appliance {
    private final int powerConsumption;

    protected ElectricAppliance(ElectricApplianceBuilder builder) {
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

        public ElectricAppliance build() {
            return new ElectricAppliance(self());
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
