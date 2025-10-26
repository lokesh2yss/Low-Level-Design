package restaurant_management_system.decorator;

public class ServiceChargeDecorator extends BillDecorator{
    private final double serviceCharge;
    public ServiceChargeDecorator(BillComponent component, double serviceCharge) {
        super(component);
        this.serviceCharge = serviceCharge;
    }
    @Override
    public double calculateTotal() {
        return super.calculateTotal() + serviceCharge;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Service Charge";
    }
}
