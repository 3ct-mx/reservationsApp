package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.muserver.*;
import org.example.model.Booking;
import org.example.service.BookingService;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BookingHandler {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private final BookingService service = new BookingService();

    public void register(MuServerBuilder server) {

        server.addHandler(Method.POST, "/bookings", (request, response, pathParams) -> {
            try {
                if (request.inputStream().isEmpty()) {
                    response.status(400);
                    response.write("Request body is required");
                    return;
                }

                Booking booking = mapper.readValue(
                        request.inputStream().orElseThrow(() -> new IllegalArgumentException("Request body is required")),
                        Booking.class
                );

                Booking created = service.create(booking);

                response.status(201);
                response.write(mapper.writeValueAsString(created));

            } catch (IllegalArgumentException e) {
                response.status(400);
                response.write(e.getMessage());
            } catch (Exception e) {
            response.status(500);
            response.write("Internal error: " + e.getMessage());
        }
        });

        server.addHandler(Method.GET, "/bookings", (request, response, pathParams) -> {
            String dateParam = request.query().get("date");

            if (dateParam == null) {
                response.status(400);
                response.write("date query param is required");
                return;
            }

            LocalDate date = LocalDate.parse(dateParam);
            List<Booking> bookings = service.getByDate(date);

            response.write(mapper.writeValueAsString(bookings));
        });
    }
}