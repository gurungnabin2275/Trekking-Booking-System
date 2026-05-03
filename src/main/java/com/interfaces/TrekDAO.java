package com.interfaces;

import com.model.Trek;
import java.util.List;

public interface TrekDAO {
    List<Trek> getAllActiveTreks();
    Trek getTrekById(int trekId);
}