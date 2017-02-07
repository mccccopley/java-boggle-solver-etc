package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class ParkingLevel {
    private static final int LaneCount = 6;
    private static final int LaneStartingOffset = 100;

    private int level;
    private ParkingLane[] lanes;

    public ParkingLevel(int level) {
        this.level = level;
        this.lanes = new ParkingLane[LaneCount];

        this.initializeLanes();
    }

    private int getLaneStartingNumber(int lane) {
        return (lane + 1) * LaneStartingOffset;
    }

    private void initializeLanes() {
        for (int laneIndex = 0; laneIndex < this.lanes.length; ++laneIndex) {
            this.lanes[laneIndex] = new ParkingLane(this.level, this.getLaneStartingNumber(laneIndex));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLane lane : this.lanes) {
            if (lane.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }
}
