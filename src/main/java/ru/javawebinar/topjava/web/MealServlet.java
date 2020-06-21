package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.MockData;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static Logger log = getLogger(MealServlet.class);
    List<Meal> mockData = new MockData().getMockData();
    Map<LocalDate, Integer> caloriesSumByDate = mockData.stream()
            .collect(
                    Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
            );
    List<MealTo> mockDataMealTo = mockData
            .stream()
            .map(m -> new MealTo(m.getDateTime(), m.getDescription(), m.getCalories(), caloriesSumByDate.get(m.getDateTime().toLocalDate()) > 2000))
            .collect(Collectors.toList());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("response: " + request.getSession());
        request.setAttribute("data", mockDataMealTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
    }
}
