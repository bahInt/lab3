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

    public void setAmountOfFlights(int amountOfFlights) {
        this.amountOfFlights = amountOfFlights;
    }

    public int getAmountOfFlights() {
        return amountOfFlights;
    }

    public static FlightSerializable addValue(FlightSerializable flight, AirportSerializable valueFlight) {
        int amountOfFlights = flight.getAmountOfFlights() + 1;
        float flightDelay = Math.max(flight.getMaxDelayTime(), valueFlight.getDelay());

        float delayedFlights;
        if(valueFlight.getFlightCancellation() || valueFlight.getDelay() > 0) {
            delayedFlights = flight.getDelayedFlights() + 1;
        } else {
            delayedFlights = flight.getDelayedFlights();
        }

        float cancelledFlights;
        if(valueFlight.getFlightCancellation()) {
            cancelledFlights = flight.getCancelledFlights() + 1;
        } else {
            cancelledFlights = flight.getCancelledFlights();
        }

        return new FlightSerializable(flightDelay, delayedFlights, cancelledFlights, amountOfFlights);
    }

    public static FlightSerializable add(FlightSerializable firstFlight, FlightSerializable secondFlight) {
        float addedMaxDelayTime;
        float addedDelayedFlights;
        float addedCancelledFlights;
        int addedAmountOfFlights;
        return new
    }
}
