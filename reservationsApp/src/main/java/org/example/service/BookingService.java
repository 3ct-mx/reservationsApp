package org.example.service;


import org.example.model.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    public Booking create(Booking booking) {
        validate(booking);
        bookings.add(booking);
        return booking;
    }

    public List<Booking> getByDate(LocalDate date) {
        return bookings.stream()
                .filter(b -> b.date().equals(date))
                .collect(Collectors.toList());
    }

    private void validate(Booking booking) {
        if (booking.customerName() == null || booking.customerName().isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }

        if (booking.tableSize() <= 0) {
            throw new IllegalArgumentException("Table size must be > 0");
        }

        if (booking.date().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }


        int hour = booking.time().getHour();
        if (hour % 2 != 0) {
            throw new IllegalArgumentException("Time must be in 2-hour intervals (e.g., 18:00, 20:00)");
        }


        boolean exists = bookings.stream().anyMatch(b ->
                b.date().equals(booking.date()) &&
                        b.time().equals(booking.time())
        );

        if (exists) {
            throw new IllegalArgumentException("Booking already exists for this time");
        }
    }
}
