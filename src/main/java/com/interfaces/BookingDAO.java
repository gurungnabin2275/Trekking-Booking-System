package com.interfaces;

import com.model.Booking;
import java.util.List;

public interface BookingDAO {
    boolean createBooking(Booking booking);
    List<Booking> getBookingsByUser(int userId);
}