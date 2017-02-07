package etc.ParkingLot;

/**
 * Created by mccccopley on 2/7/2017.
 */
public class BusFactory implements IVehicleFactory {
    public Vehicle allocateVehicle() { return new Bus(); }
}
