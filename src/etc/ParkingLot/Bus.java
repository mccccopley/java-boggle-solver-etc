package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class Bus extends Vehicle {
    public Bus() {
        super(VehicleSize.Bus);
        initializeParkingSpaces(6);
    }

    @Override
    public boolean canParkInSpaceType(ParkingSpaceType spaceType) {
        return spaceType == ParkingSpaceType.Regular;
    }
}
