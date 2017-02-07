package etc.ParkingLot;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by mccccopley on 2/7/2017.
 */
public abstract class Vehicle {
    protected VehicleSize size;
    protected Instant arrivalTime;
    protected ParkingSpace[] occupiedSpaces;

    protected Vehicle(VehicleSize size) {
        this.size = size;
        this.arrivalTime = Clock.systemUTC().instant();
    }

    protected void initializeParkingSpaces(int requiredSpaces) {
        this.occupiedSpaces = new ParkingSpace[requiredSpaces];
    }

    public VehicleSize getSize() {
        return this.size;
    }

    public int getRequiredSpaces() {
        return occupiedSpaces.length;
    }

    public long calculateCost(int hourlyCost) {
        Instant now = Clock.systemUTC().instant();
        long hours = Duration.between(this.arrivalTime, now).toHours();
        return hours * hourlyCost;
    }

    public void departSpaces() {
        int endIndex = this.getRequiredSpaces();
        for (int index = 0; index < endIndex; ++index) {
            this.occupiedSpaces[index].occupyByVehicle(null);
            this.occupiedSpaces[index] = null;
        }
    }

    public void occupySpaces(ParkingSpace[] spaces, int startingIndex) {
        int endIndex = this.getRequiredSpaces();
        for (int index = 0; index < endIndex; ++index) {
            int sourceIndex = startingIndex + index;
            spaces[sourceIndex].occupyByVehicle(this);
            this.occupiedSpaces[index] = spaces[sourceIndex];
        }
    }

    public abstract boolean canParkInSpaceType(ParkingSpaceType spaceType);
}
