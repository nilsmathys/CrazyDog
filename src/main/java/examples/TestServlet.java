package examples;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * This is just an example-servlet to show that servlets are working.
 */

@WebServlet(name = "FormServlet", urlPatterns = "/calculateServlet")
public class TestServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doPost of servlet entered.");

        String height = request.getParameter("height");
        String weight = request.getParameter("weight");

        try {
            double bmi = calculateBMI( Double.parseDouble(weight), Double.parseDouble(height));
            System.out.println(bmi);
            request.setAttribute("bmi", bmi);
            response.setHeader("Bmi", String.valueOf(bmi));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/example.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("There was an exception");
            response.sendRedirect("index.jsp");
        }
    }

    private Double calculateBMI(Double weight, Double height) {
        return weight / (height * height);
    }
}
