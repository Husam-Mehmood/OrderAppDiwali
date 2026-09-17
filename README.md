# Diwali Restaurant — Order Management System

A Java Swing desktop application that simulates a restaurant's front-of-house and kitchen workflow, built as an Object-Oriented Software Design (OOSD) project. It combines a GUI ordering flow with a real multithreaded producer–consumer backend.

## Features

- **Login / Sign-up flow** on a themed home screen with a rotating background image carousel.
- **Takeaway or Dine-in** order paths leading into a menu selection screen.
- **Concurrent order processing:** `Waiter` and `Chef` run as separate threads, synchronized over a shared `Restaurant` object using `wait()`/`notify()` — a classic producer–consumer pattern. The waiter queues orders; the chef picks them up, "prepares" them, and signals completion back to the waiter.

## Architecture

| Class | Responsibility |
|---|---|
| `RestarauntHomePage` | Main JFrame — login/sign-up, background rotation, entry point for Takeaway/Dine-in |
| `RestaurantMenuPage` | Menu selection screen shown after login |
| `Restaraunt` | Shared state: order queue, synchronization logic between Waiter and Chef |
| `Waiter` | Thread that pulls orders from the queue and hands them to the chef |
| `Chef` | Thread that "prepares" the current order and notifies the waiter when done |
| `OrderItem` | Simple order data object (factory-constructed) |

## Tech stack
Java, Swing (AWT/Swing GUI), `java.util.concurrent`-style threading primitives (`Thread`, `wait()`/`notify()`).

## Running it

```bash
# From the project root
javac -d out/production/OOSD3_CA3 src/*.java
java -cp out/production/OOSD3_CA3 RestarauntHomePage
```
> Note: image assets are loaded from `imgsrc/` using relative paths, so run from the project root.

## Project background
Built as coursework for Object-Oriented Software Design (OOSD), focused on applying threading and synchronization to a GUI application.
