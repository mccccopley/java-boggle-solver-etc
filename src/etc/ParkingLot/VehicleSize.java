package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public enum VehicleSize {
    Motorcycle(0),
    PassengerCar(1),
    Bus(2);

    private final int value;

    VehicleSize(int value) { this.value = value; }
    public int getValue() { return this.value; }
}
