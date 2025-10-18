package traffic_control_system.entities;

import traffic_control_system.enums.Direction;
import traffic_control_system.enums.LightColor;
import traffic_control_system.observer.TrafficObserver;
import traffic_control_system.state.GreenState;
import traffic_control_system.state.RedState;
import traffic_control_system.state.SignalState;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
    private final Direction direction;
    private LightColor currentColor;
    private SignalState currentState;
    private SignalState nextState; // The state to transition to after a timer elapses
    private final List<TrafficObserver> observers = new ArrayList<>();
    private final int intersectionId;

    public TrafficLight(int intersectionId, Direction direction) {
        this.intersectionId = intersectionId;
        this.direction = direction;
        this.currentState = new RedState(); //default is red state;
        this.currentState.handle(this);
    }
    // This is called by the IntersectionController to initiate a G-Y-R cycle
    public void startGreen() {
        this.currentState = new GreenState();
        this.currentState.handle(this);
    }
    // This is called by the IntersectionController to transition from G->Y or Y->R
    public void transition() {
        this.currentState = this.nextState;
        this.currentState.handle(this);
    }
    public void setColor(LightColor color) {
        if(this.currentColor != color) {
            this.currentColor = color;
            notifyObservers();
        }
    }

    public void setNextState(SignalState nextState) {
        this.nextState = nextState;
    }

    public Direction getDirection() {
        return direction;
    }

    public LightColor getCurrentColor() {
        return currentColor;
    }
    // Observer pattern methods
    public void addObserver(TrafficObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(TrafficObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for(TrafficObserver observer: observers) {
            observer.update(intersectionId, direction, currentColor);
        }
    }
}
