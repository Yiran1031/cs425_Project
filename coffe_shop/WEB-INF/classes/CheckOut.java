import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/CheckOut")

public class CheckOut extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(true);

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String customerid=request.getParameter("customerid");
		String point = request.getParameter("earnedpoint");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(new Date());
		
	 	utility.printHtml("Head.html");
        utility.printHtml("Title.html");
		pw.print("<div class = 'content'><div class='home'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		int orderId=utility.getTransactionSize()+1;

				//iterate through each order

		for (String oi : utility.getCustomerOrders())
		{
			utility.storeTransaction(orderId,customerid,oi,today);	
			utility.storeMinus(oi);		
		}
		utility.addPoint(customerid,Integer.parseInt(point));	
		OrdersHashMap.orders.remove(utility.getUserid());		
		pw.print("<h4>Transaction");
		pw.print("&nbsp&nbsp");  
		pw.print("is stored ");
		pw.print("<br>The transaction No is "+(orderId)+"</h4>");
		pw.print("</div></div></div>");	
		utility.printHtml("Footer.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
