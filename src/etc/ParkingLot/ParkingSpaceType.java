package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public enum ParkingSpaceType {
    Motorcycle(0),
    Compact(1),
    Regular(2);

    private final int value;

    ParkingSpaceType(int value) { this.value = value; }
    public int getValue() { return this.value; }
    public static ParkingSpaceType getFromValue(int value) {
        ParkingSpaceType[] values = ParkingSpaceType.values();
        for (ParkingSpaceType type : values) {
            if( type.value == value) {
                return type;
            }
        }
        return null;
    }
}
