import java.io.Serializable;

public class AirportSerializable implements Serializable {
    private int departureAirportID;
    private int destinationAirportID;
    private float delay;
    private boolean flightCancellation;

    public AirportSerializable(int departureAirportID, int destinationAirportID, float delay, boolean flightCancellation) {
        this.departureAirportID = departureAirportID;
        this.destinationAirportID = destinationAirportID;
        this.delay = delay;
        this.flightCancellation = flightCancellation;
    }

    public int setDepartureAirportID(){}
    public int getDepartureAirportID(){}

    public int setDestinationAirportID(){}
    public int getDestinationAirportID(){}

    public float setDelay(){}
    public float getDelay(){}

    public boolean setFlightCancellation(){}
    public boolean getFlightCancellation(){}
}
