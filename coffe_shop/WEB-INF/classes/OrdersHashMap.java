import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrdersHashMap")

public class OrdersHashMap extends HttpServlet{

	public static HashMap<String, ArrayList<String>> orders = new HashMap<String, ArrayList<String>>();
	
	public OrdersHashMap() {
		
	}	
}
