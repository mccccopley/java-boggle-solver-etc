package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class ParkingLot {
    private static final int LevelCount = 5;
    private static final int HourlyRate = 3;

    private final IVehicleFactory vehicleFactories[] = new IVehicleFactory[3];

    private ParkingLevel[] levels;

    public ParkingLot() {
        this.levels = new ParkingLevel[LevelCount];
        this.vehicleFactories[VehicleSize.Motorcycle.getValue()] = new MotorcycleFactory();
        this.vehicleFactories[VehicleSize.PassengerCar.getValue()] = new PassengerCarFactory();
        this.vehicleFactories[VehicleSize.Bus.getValue()] = new BusFactory();
    }

    // returns the successfully parked vehicle, or null if parking failed
    public Vehicle parkVehicle(VehicleSize size) {
        Vehicle vehicle = this.vehicleFactories[size.getValue()].allocateVehicle();
        for (ParkingLevel level : this.levels) {
            if (level.parkVehicle(vehicle)) {
                return vehicle;
            }
        }
        return null;
    }

    // returns the cost for the parked vehicle that was released
    public long releaseVehicle(Vehicle vehicle) {
        long costToPark = vehicle.calculateCost(HourlyRate);
        vehicle.departSpaces();
        return costToPark;
    }
}
