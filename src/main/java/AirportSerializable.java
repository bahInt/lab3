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

    public void setDepartureAirportID(int departureAirportID){
        this.departureAirportID = departureAirportID;
    }
    public int getDepartureAirportID(){
        return departureAirportID;
    }

    public void setDestinationAirportID(int destinationAirportID){
        this.destinationAirportID = destinationAirportID;
    }
    public int getDestinationAirportID(){
        return destinationAirportID;
    }

    public void setDelay(float delay){
        this.delay = delay;
    }
    public float getDelay(){
        return delay;
    }

    public void setFlightCancellation(boolean flightCancellation){
        this.flightCancellation = flightCancellation;
    }
    public boolean getFlightCancellation(){}
}
