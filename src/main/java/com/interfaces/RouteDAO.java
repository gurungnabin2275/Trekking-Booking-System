package com.interfaces;

import com.model.Route;
import java.util.List;

public interface RouteDAO {
    List<Route> getRoutesByTrek(int trekId);
}