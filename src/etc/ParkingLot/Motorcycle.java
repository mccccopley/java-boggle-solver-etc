package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class Motorcycle extends Vehicle {
    public Motorcycle() {
        super(VehicleSize.Motorcycle);
        initializeParkingSpaces(1);
    }

    @Override
    public boolean canParkInSpaceType(ParkingSpaceType spaceType) {
        return true;
    }
}
