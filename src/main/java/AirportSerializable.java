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

    public int setDepartureAirportID(int departureAirportID){
        this.departureAirportID = departureAirportID;
    }
    public int getDepartureAirportID(){
        
    }

    public int setDestinationAirportID(int destinationAirportID){
        this.destinationAirportID = destinationAirportID;
    }
    public int getDestinationAirportID(){}

    public float setDelay(float delay){
        this.delay = delay;
    }
    public float getDelay(){}

    public boolean setFlightCancellation(boolean flightCancellation){
        this.flightCancellation = flightCancellation;
    }
    public boolean getFlightCancellation(){}
}
