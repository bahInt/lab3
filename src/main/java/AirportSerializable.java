import java.io.Serializable;

public class AirportSerializable implements Serializable {
    private int departureAirportID;
    private int destinationAirportID;
    private float delay;
    private boolean isFlightCancelled;

    public AirportSerializable(int departureAirportID, int destinationAirportID, float delay, boolean isFlightCancelled) {
        this.departureAirportID = departureAirportID;
        this.destinationAirportID = destinationAirportID;
        this.delay = delay;
        this.isFlightCancelled = isFlightCancelled;
    }
}
