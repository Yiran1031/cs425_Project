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

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String product_id = request.getParameter("product_id");
		// String type = request.getParameter("type");
		// String price = request.getParameter("price");
		// String image = request.getParameter("image");
		

		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
		utility.storeToList(product_id);
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Product product = new Product();
		String customerid = request.getParameter("customerid");

		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("LoginServlet");
			return;
		}
        HashMap<String,Product> allProduct = new HashMap<String,Product> ();
        try{
        allProduct = SqlDataStoreUtilities.getAllProduct();            
        }
        catch(Exception e){
            e.printStackTrace();
        }	
	 	utility.printHtml("Head.html");
        utility.printHtml("Title.html");

        pw.print("<div class = 'content'><div class='home'>");
		pw.print("<div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>List("+utility.listCount()+")</a>");
		pw.print("</h2><div class='entry'>");

		if(utility.listCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			int point = 0;
			double totalPrice = 0;
			int product_total = 0;
			for (String product_id : utility.getCustomerOrders()) 
			{
				product = allProduct.get(product_id);
				pw.print("<form action='RemoveCoffee' method='post'>");

				pw.print("<tr>");
				pw.print("<td>"+i+".</td><td>"+product.getName()+"</td><td>: "+product.getPrice()+"</td>");

				pw.print("<td><input type='submit' name='removeItems' value='Remove' class='btnbuy' /></td>");

				pw.print("<input type='hidden' name='productid' value='"+product.getProduct_id()+"'>");
				pw.print("</tr></form>");
				totalPrice = totalPrice +product.getPrice();
				i++;
			}

			if (utility.findCustomer(customerid) != null){
				pw.print("<form name ='Cart' action='CheckOut' method='post'>");
				if(utility.checkPoint(customerid))
				{
					pw.print("<tr><th></th><th>The customer can get a free coffee:</th><th>"+product.getName()+"</th>");
					pw.print("<input type='hidden' name='totalprice' value='"+(totalPrice-product.getPrice())+"'>");	
					pw.print("<input type='hidden' name='earnedpoint' value='"+(int)(totalPrice-product.getPrice())+"'>");	
					pw.print("<tr><th></th><th>Total</th><th><h4 style='color:red'>"+(totalPrice-product.getPrice())+"</h4></th>");
					pw.print("<tr><th></th><th>Earned Point:</th><th>"+(int)(totalPrice-product.getPrice())+"</th>");
					utility.minusPoint(customerid);
				}else{
					pw.print("<input type='hidden' name='totalprice' value='"+totalPrice+"'>");	
					pw.print("<input type='hidden' name='earnedpoint' value='"+(int)totalPrice+"'>");	
					pw.print("<tr><th></th><th>Total</th><th>"+totalPrice+"</th>");
					pw.print("<tr><th></th><th>Earned Point:</th><th>"+(int)totalPrice+"</th>");
				}
				Customer customer = utility.findCustomer(customerid);
				pw.print("<tr><th></th><th>Customer id:</th><th>"+customer.getCustomerId()+"</th>");
				pw.print("<input type='hidden' name='customerid' value='"+customer.getCustomerId()+"'>");		
				pw.print("<tr><th></th><th>Customer name:</th><th>"+customer.getFirstname()+"</th>");
				pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
				pw.print("</table></form>");
			}else{
				pw.print("<form name ='Cart' action='Cart' method='get'>");
				pw.print("<input type='hidden' name='totalprice' value='"+totalPrice+"'>");	
				pw.print("<input type='hidden' name='earnedpoint' value='"+(int)totalPrice+"'>");	
				pw.print("<tr><th></th><th>Total</th><th>"+totalPrice+"</th>");
				pw.print("<tr><th></th><th>Earned Point:</th><th>"+(int)totalPrice+"</th>");
				pw.print("<tr><th></th><th>Customer id:</th><th><input class='form-control required' type='text' placeholder='customer number' name='customerid' autofocus='autofocus' required/></th>");
				pw.print("<tr><td></td><td></td><td><input type='submit' name='nextstep' value='nextstep' class='btnbuy' /></td>");
				pw.print("</table></form>");
			}
		}
		else
		{
			pw.print("<h4 style='color:red'>The coffee list is empty</h4>");
		}
		 pw.print("</div></div></div></div>");
         utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);		
		displayCart(request, response);
	}
}
