package lab3;

import java.io.Serializable;

public class FlightSerializable implements Serializable {
    private float maxDelayTime;
    private float delayedFlights;
    private float cancelledFlights;
    private int amountOfFlights;

    public FlightSerializable(float maxDelayTime, float delayedFlights, float cancelledFlights, int amountOfFlights) {
        this.maxDelayTime = maxDelayTime;
    }
}
