package com.controller.servlets;

import com.dao.TrekDAOImpl;
import com.dao.RouteDAOImpl;
import com.dao.ReviewDAOImpl;
import com.interfaces.TrekDAO;
import com.interfaces.RouteDAO;
import com.interfaces.ReviewDAO;
import com.model.Trek;
import com.model.Route;
import com.model.Review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/TrekServlet")
public class TrekServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        TrekDAO trekDAO = new TrekDAOImpl();

        if (action == null || action.equals("list")) {
            List<Trek> treks = trekDAO.getAllActiveTreks();
            request.setAttribute("treks", treks);
            request.getRequestDispatcher("treks.jsp").forward(request, response);

        } else if (action.equals("detail")) {
            int trekId = Integer.parseInt(request.getParameter("trekId"));
            Trek trek  = trekDAO.getTrekById(trekId);

            RouteDAO  routeDAO  = new RouteDAOImpl();
            ReviewDAO reviewDAO = new ReviewDAOImpl();

            List<Route>  routes  = routeDAO.getRoutesByTrek(trekId);
            List<Review> reviews = reviewDAO.getReviewsByTrek(trekId);

            request.setAttribute("trek",    trek);
            request.setAttribute("routes",  routes);
            request.setAttribute("reviews", reviews);
            request.getRequestDispatcher("trekDetail.jsp").forward(request, response);
        }
    }
}