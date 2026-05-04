package com.interfaces;

import com.model.Trek;
import java.util.List;

public interface TrekDAO {
    List<Trek> getAllActiveTreks();
    Trek getTrekById(int trekId);
    boolean addTrek(Trek trek);       // admin
    boolean updateTrek(Trek trek);    // admin
    boolean deleteTrek(int trekId);   // admin
    List<Trek> getAllTreks();          // admin (includes inactive)
}