package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class PassengerCar extends Vehicle {
    public PassengerCar() {
        super(VehicleSize.PassengerCar);
        initializeParkingSpaces(1);
    }

    @Override
    public boolean canParkInSpaceType(ParkingSpaceType spaceType) {
        return spaceType == ParkingSpaceType.Compact || spaceType == ParkingSpaceType.Regular;
    }
}
