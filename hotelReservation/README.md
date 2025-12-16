# Hotel Reservation System

## Description
A simplified hotel reservation system implemented in Java.

## Entities
- User (balance)
- Room (type, price per night)
- Booking (user, room, period, total price)

## Design Questions

### 1. Should all functions be in the same service?
Putting all functions inside a single service works for a small test,
but it is not recommended in real applications.
It breaks the Single Responsibility Principle.
A better approach would be to separate concerns into multiple services.

### 2. setRoom(...) should not impact previous bookings. Another way?
Another approach is to store a snapshot of room data inside the Booking
entity at booking time.
This ensures previous bookings remain unchanged even if room data is
updated later.
