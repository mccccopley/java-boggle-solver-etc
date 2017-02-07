package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class ParkingSpace {
    private ParkingSpaceType type;
    private int level;
    private int number;
    private Vehicle occupyingVehicle;

    public ParkingSpace(ParkingSpaceType type, int level, int number) {
        this.type = type;
        this.level = level;
        this.number = number;
        this.occupyingVehicle = null;
    }

    public ParkingSpaceType getType() {
        return this.type;
    }

    public boolean isOccupied() {
        return this.occupyingVehicle != null;
    }

    public void occupyByVehicle(Vehicle vehicle) {
        this.occupyingVehicle = vehicle;
    }
}
