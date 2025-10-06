package elevator_system.entities;

import elevator_system.enums.Direction;
import elevator_system.observer.ElevatorObserver;
import elevator_system.state.ElevatorState;
import elevator_system.state.IdleState;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator implements Runnable{
    private final int id;
    private AtomicInteger currentFloor;
    private ElevatorState state;

    private volatile boolean isRunning;

    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    private final List<ElevatorObserver> observers = new ArrayList<>();
    public Elevator(int id) {
        this.id = id;
        this.currentFloor = new AtomicInteger();
        this.state = new IdleState();
        this.isRunning = false;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>((a,b) -> b-a);
    }

    public int getId() {
        return id;
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
        observer.update(this); //send initial state
    }
    public void notifyObservers() {
        for(ElevatorObserver observer: observers) {
            observer.update(this);
        }
    }
    public void setState(ElevatorState state) {
        this.state = state;
        notifyObservers(); //notify observers on direction change
    }
    public void move() {
        state.move(this);
    }
    public synchronized void addRequest(Request request) {
        System.out.println("Elevator " + id + " processing: " + request);
        state.addRequest(this, request);
    }
    public int getCurrentFloor() {
        return currentFloor.get();
    }
    public void setCurrentFloor(int floor) {
        this.currentFloor.set(floor);
        notifyObservers();
    }
    public Direction getDirection() {
        return state.getDirection();
    }

    public TreeSet<Integer> getUpRequests() {
        return upRequests;
    }

    public TreeSet<Integer> getDownRequests() {
        return downRequests;
    }

    public boolean isRunning() {
        return isRunning;
    }
    public void stopElevator() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            move();
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
