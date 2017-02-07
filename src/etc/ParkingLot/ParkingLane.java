package etc.ParkingLot;

import static java.lang.Integer.max;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class ParkingLane {
    private static final int[] SpaceCounts = new int[] {5, 10, 10};

    private int level;
    private int startingNumber;
    private ParkingSpace[] spaces;

    public ParkingLane(int level, int startingNumber) {

        this.level = level;
        this.startingNumber = startingNumber;

        int totalSpaces = 0;
        for (int spaceCount : SpaceCounts) {
            totalSpaces += spaceCount;
        }

        this.spaces = new ParkingSpace[totalSpaces];
        this.initializeParkingSpaces(ParkingSpaceType.Motorcycle);
        this.initializeParkingSpaces(ParkingSpaceType.Compact);
        this.initializeParkingSpaces(ParkingSpaceType.Regular);
    }

    private void initializeParkingSpaces(ParkingSpaceType type) {
        int spaceTypeOffset = getSpaceTypeStartingIndex(type);
        int spaceTypeCount = SpaceCounts[type.getValue()];
        for (int index = 0; index < spaceTypeCount; ++index) {
            int arrayIndex = index + spaceTypeOffset;
            int spaceNumber = this.startingNumber + arrayIndex;
            this.spaces[spaceTypeOffset + index] = new ParkingSpace(type, this.level, spaceNumber);
        }
    }

    private int getSpaceTypeStartingIndex(ParkingSpaceType type) {
        int spaceTypeOffset = 0;
        for (int spaceTypeIndex = 0; spaceTypeIndex < SpaceCounts.length; ++spaceTypeIndex) {
            if (spaceTypeIndex == type.getValue()) {
                break;
            } else {
                spaceTypeOffset += SpaceCounts[spaceTypeIndex];
            }
        }
        return spaceTypeOffset;
    }

    public int getTotalSpaces() {
        return this.spaces.length;
    }

    public int getTotalSpacesOfType(ParkingSpaceType type) {
        return SpaceCounts[type.getValue()];
    }

    public int getAvailableBlockOfSpacesForCount(ParkingSpaceType type, int count) {
        int spaceTypeOffset = this.getSpaceTypeStartingIndex(type);
        int spaceTypeCount = SpaceCounts[type.getValue()];
        int spaceCount = 0;
        for (int index = 0; index < spaceTypeCount; ++index) {
            int arrayIndex = index + spaceTypeOffset;
            if (this.spaces[arrayIndex].isOccupied()) {
                spaceCount = 0;
            } else {
                ++spaceCount;
                if (spaceCount == count) {
                    return arrayIndex;
                }
            }
        }
        return -1;
    }

    // attempts to park the vehicle in this lane as best as possible, and returns false on failure
    public boolean parkVehicle(Vehicle vehicle) {
        for (int spaceTypeValue = 0; spaceTypeValue < SpaceCounts.length; ++spaceTypeValue) {
            ParkingSpaceType spaceType = ParkingSpaceType.getFromValue(spaceTypeValue);
            if (vehicle.canParkInSpaceType(spaceType)) {
                int availableSpaceBlockIndex = this.getAvailableBlockOfSpacesForCount(spaceType, vehicle.getRequiredSpaces());
                if (availableSpaceBlockIndex != -1) {
                    vehicle.occupySpaces(this.spaces, availableSpaceBlockIndex);
                    return true;
                }
            }
        }
        return false;
    }
}
