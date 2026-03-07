# Low Level Design (LLD) – Java Implementation

This repository contains implementations of common **Low Level Design (LLD) / Object-Oriented Design (OOD)** problems using Java.

The goal of this repository is to practice designing maintainable and scalable systems using **object-oriented principles, clean architecture, and modular design**.

The repository includes implementations of commonly asked **machine-coding and LLD interview problems**.

---

## Implemented Systems

### Parking Lot System

A scalable parking lot system supporting multiple vehicle types and parking spot allocation.

Key features:

- Vehicle entry and exit management
- Different vehicle types (car, bike, truck)
- Parking spot allocation
- Parking ticket generation
- Parking fee calculation

Concepts covered:

- Object-oriented modeling
- Encapsulation of parking logic
- Separation of concerns
- Modular system design

---

### Snakes and Ladders

Object-oriented design implementation of the classic board game.

Concepts covered:

- Game state management
- Player abstraction
- Dice rolling logic
- Turn-based gameplay
- Board representation

---

### Course Scheduling System

Design a system that manages course dependencies and scheduling.

Concepts covered:

- Graph modeling
- Dependency resolution
- Topological sorting
- Scheduling workflows

---

# UML Class Diagram – Parking Lot

classDiagram

class ParkingLot {
  -List~ParkingFloor~ floors
  +addFloor()
  +parkVehicle()
  +unparkVehicle()
}

class ParkingFloor {
  -List~ParkingSpot~ spots
  +getAvailableSpot()
}

class ParkingSpot {
  -int spotId
  -Vehicle vehicle
  +assignVehicle()
  +removeVehicle()
}

class Vehicle {
  -String licenseNumber
  -VehicleType type
}

class Ticket {
  -String ticketId
  -Date entryTime
  -Vehicle vehicle
}

class Payment {
  -double amount
  +calculateFee()
}

ParkingLot --> ParkingFloor
ParkingFloor --> ParkingSpot
ParkingSpot --> Vehicle
Ticket --> Vehicle
Payment --> Ticket

Design Principles Used

The implementations emphasize fundamental software design concepts:

SOLID Principles

Encapsulation and abstraction

Separation of concerns

Modular class design

Clean code practices

Tech Stack

Language:

Java

Concepts:

Object-Oriented Programming

Low-Level Design

Design Patterns

System Modeling

Repository Structure

low-level-design
│
├── parking-lot
│   ├── ParkingLot.java
│   ├── ParkingFloor.java
│   ├── ParkingSpot.java
│   ├── Vehicle.java
│
├── snakes-and-ladders
│   ├── Game.java
│   ├── Player.java
│   ├── Dice.java
│
├── course-scheduling
│   ├── Course.java
│   ├── Scheduler.java


Each directory represents a self-contained system design problem implementation.

Purpose of This Repository

This repository was created as part of preparation for software engineering interviews focusing on system design and backend engineering.

The objective is to:

Practice low-level system design

Model real-world systems using object-oriented programming

Build maintainable and extensible software architectures

Related Backend System Design Projects

You may also find these repositories interesting:

LinkedIn Microservices Backend System
https://github.com/lokesh2yss/linkedin-microservices-backend

Uber Backend System
https://github.com/lokesh2yss/uber-backend-system

Zomato Backend System
https://github.com/lokesh2yss/zomato-backend-system

Author

Lokesh Kumar
Backend Engineer | Java | Spring Boot | Distributed Systems

GitHub: https://github.com/lokesh2yss

LinkedIn: https://linkedin.com/in/lokeshtalks


---
