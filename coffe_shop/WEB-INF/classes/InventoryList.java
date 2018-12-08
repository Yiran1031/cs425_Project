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

@WebServlet("/InventoryList")

public class InventoryList extends HttpServlet {
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
		HashMap<String,Product> all_product = new HashMap<String,Product>();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login first");
			response.sendRedirect("LoginServlet");
			return;
		}
		try{
		   all_product = SqlDataStoreUtilities.getAllProduct();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 	utility.printHtml("Head.html");
        utility.printHtml("Title.html");

        pw.print("<div class = 'content'><div class='home'>");
		pw.print("<div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Inventory report</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'>");
		pw.print("<td>product id.</td><td>product name</td><td>Inventory</td>");
		int i = 1;
		for (Map.Entry<String, Product> entry : all_product.entrySet())
		{
			Product product = entry.getValue();
			pw.print("<tr>");
			pw.print("<td> "+product.getProduct_id()+".</td><td> "+product.getName()+"</td><td> "+product.getStock_num()+"</td>");
			pw.print("</tr>");
			i++;
		}
		pw.print("</table>");
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
