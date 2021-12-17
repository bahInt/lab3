package lab3;

import java.io.Serializable;

public class FlightSerializable implements Serializable {
    private float maxDelayTime;
    private float delayedFlights;
    private float cancelledFlights;
    private int amountOfFlights;

    public FlightSerializable(float maxDelayTime, float delayedFlights, float cancelledFlights, int amountOfFlights) {
        this.maxDelayTime = maxDelayTime;
        this.delayedFlights = delayedFlights;
        this.cancelledFlights = cancelledFlights;
        this.amountOfFlights = amountOfFlights;
    }

    public void setMaxDelayTime(float maxDelayTime) {
        this.maxDelayTime = maxDelayTime;
    }

    public float getMaxDelayTime() {
        return maxDelayTime;
    }

    public void setDelayedFlights(float delayedFlights) {
        this.delayedFlights = delayedFlights;
    }

    public float getDelayedFlights() {
        return delayedFlights;
    }

    public void setCancelledFlights(float cancelledFlights) {
        this.cancelledFlights = cancelledFlights;
    }

    public float getCancelledFlights() {
        return cancelledFlights;
    }
}
