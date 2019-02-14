package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealTo> meals = MealsUtil.getFilteredWithExcess(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meals", meals);
        req.setAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd' 'hh:mm"));
        LOG.debug("forwrd to meals:" + meals);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);

    }
}
