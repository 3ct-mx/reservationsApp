package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record Booking(
        String customerName,
        int tableSize,
        LocalDate date,
        LocalTime time
) {}