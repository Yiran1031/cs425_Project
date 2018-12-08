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

@WebServlet("/DailySaleList")

public class DailySaleList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		displayList(request, response);
	}
	
	protected void displayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
// 		Product product = new Product();
// 		String customerid = request.getParameter("customerid");

		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login first");
			response.sendRedirect("LoginServlet");
			return;
		}
//         HashMap<String,Product> allProduct = new HashMap<String,Product> ();
//         try{
//         allProduct = SqlDataStoreUtilities.getAllProduct();            
//         }
//         catch(Exception e){
//             e.printStackTrace();
//         }	
	 	utility.printHtml("Head.html");
        utility.printHtml("Title.html");

        pw.print("<div class = 'content'><div class='home'>");
		pw.print("<div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Sales report</a>");
		pw.print("</h2><div class='entry'>");

// 		if(utility.listCount()>0)
// 		{
			pw.print("<table  class='gridtable'>");
			pw.print("<td>product id.</td><td>product name</td><td>product price</td><td>date</td><td>Sale number</td><td>total sale</td>");
			int i = 1;
// 			int point = 0;
// 			double totalPrice = 0;
// 			int product_total = 0;
			for (DailySale ds : SqlDataStoreUtilities.getDailySale()) 
			{

				pw.print("<tr>");
				pw.print("<td> "+ds.getProduct_id()+".</td><td> "+ds.getName()+"</td><td>$ "+ds.getPrice()+"</td><td> "+ds.getDate()+"</td><td> "+ds.getSale()+"</td><td>$ "+(ds.getPrice()*ds.getSale())+"</td>");
				pw.print("</tr>");
				i++;
			}
			pw.print("</table>");

// 			if (utility.findCustomer(customerid) != null){
// 				pw.print("<form name ='Cart' action='CheckOut' method='post'>");
// 				if(utility.checkPoint(customerid))
// 				{
// 					pw.print("<tr><th></th><th>The customer can get a free coffee:</th><th>"+product.getName()+"</th>");
// 					pw.print("<input type='hidden' name='totalprice' value='"+(totalPrice-product.getPrice())+"'>");	
// 					pw.print("<input type='hidden' name='earnedpoint' value='"+(int)(totalPrice-product.getPrice())+"'>");	
// 					pw.print("<tr><th></th><th>Total</th><th><h4 style='color:red'>"+(totalPrice-product.getPrice())+"</h4></th>");
// 					pw.print("<tr><th></th><th>Earned Point:</th><th>"+(int)(totalPrice-product.getPrice())+"</th>");
// 					utility.minusPoint(customerid);
// 				}else{
// 					pw.print("<input type='hidden' name='totalprice' value='"+totalPrice+"'>");	
// 					pw.print("<input type='hidden' name='earnedpoint' value='"+(int)totalPrice+"'>");	
// 					pw.print("<tr><th></th><th>Total</th><th>"+totalPrice+"</th>");
// 					pw.print("<tr><th></th><th>Earned Point:</th><th>"+(int)totalPrice+"</th>");
// 				}
// 				Customer customer = utility.findCustomer(customerid);
// 				pw.print("<tr><th></th><th>Customer id:</th><th>"+customer.getCustomerId()+"</th>");
// 				pw.print("<input type='hidden' name='customerid' value='"+customer.getCustomerId()+"'>");		
// 				pw.print("<tr><th></th><th>Customer name:</th><th>"+customer.getFirstname()+"</th>");
// 				pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
// 				pw.print("</table></form>");
// 			}else{
// 				pw.print("<form name ='Cart' action='Cart' method='get'>");
// 				pw.print("<input type='hidden' name='totalprice' value='"+totalPrice+"'>");	
// 				pw.print("<input type='hidden' name='earnedpoint' value='"+(int)totalPrice+"'>");	
// 				pw.print("<tr><th></th><th>Total</th><th>"+totalPrice+"</th>");
// 				pw.print("<tr><th></th><th>Earned Point:</th><th>"+(int)totalPrice+"</th>");
// 				pw.print("<tr><th></th><th>Customer id:</th><th><input class='form-control required' type='text' placeholder='customer number' name='customerid' autofocus='autofocus' required/></th>");
// 				pw.print("<tr><td></td><td></td><td><input type='submit' name='nextstep' value='nextstep' class='btnbuy' /></td>");
// 				pw.print("</table></form>");
// 			}
// 		}
// 		else
// 		{
// 			pw.print("<h4 style='color:red'>The coffee list is empty</h4>");
// 		}
		 pw.print("</div></div></div></div>");
         utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);		
		displayList(request, response);
	}
}
