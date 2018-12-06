import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;



@WebServlet("/RemoveCoffee")

public class RemoveCoffee extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String productid = request.getParameter("productid");
        String userid = utility.getUserid();
            
        ArrayList<String> order = OrdersHashMap.orders.get(userid);
        int index = 0;
        for (String p_id : order) {
           if (p_id.equals(productid)) {
                break;
            } else index++;
        }
        System.out.println("index:"+index);
        order.remove(index);          
        response.sendRedirect("Cart");

	}
}