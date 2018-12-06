import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductList")

public class ProductList extends HttpServlet {

	/* Trending Page Displays all the Tablets and their Information */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HashMap<String,Product> all_product = new HashMap<String,Product> ();
	
		try{
		     all_product = SqlDataStoreUtilities.getAllProduct();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// HashMap<String,Service> hm = new HashMap<String,Service>();
		// for(Map.Entry<String,Service> entry : allservice.entrySet())
		// {
		// 	hm.put(entry.getValue().getId(),entry.getValue());
		// 	System.out.println(entry.getValue().getName());
		// }

		Utilities utility = new Utilities(request, pw);
	 	utility.printHtml("Head.html");
        utility.printHtml("Title.html");

        pw.print("<div class = 'content'><div class='home'>");
		pw.print("<a style='font-size: 24px;'>Coffee</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = all_product.size();
		// double fprice;
		for (Map.Entry<String, Product> entry : all_product.entrySet()) {
			Product product = entry.getValue();
			if (i % 3 == 1)
		 		pw.print("<tr>");

	 		pw.print("<td><div id='shop_item'>");
	 		pw.print("<h3>" + product.getName() + "</h3>");
	 		pw.print("<strong>Price:$" + product.getPrice() + "</strong><ul>");
	 		pw.print("<li id='item'><img src='images/"+ product.getImage() + "' alt='' /></li>");
	 		pw.print("<li><form method='post' action='Cart'>" +
		 			"<input type='hidden' name='product_id' value='"+product.getProduct_id()+"'>"+
		 			"<input type='hidden' name='price' value='"+product.getPrice()+"'>"+
		 			"<input type='hidden' name='image' value='"+product.getImage()+"'>"+
		 			"<input type='hidden' name='type' value='"+product.getCategory()+"'>"+
		 			"<input type='submit' class='btnbuy' value='Buy'></form></li>");
		 		pw.print("</ul></div></td>");
		 	if (i % 3 == 0 || i == size)
	 			pw.print("</tr>");
		 	i++;
		}
		 pw.print("</table></div></div></div>");
         utility.printHtml("Footer.html");
	}
}
