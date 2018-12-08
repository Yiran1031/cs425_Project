import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DailySale")


public class DailySale extends HttpServlet{
	String product_id;
	String name;
	double price;
	String date;
	int sale;
	
	public DailySale(String product_id,String name,Double price,String date,int sale){
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.date = date;
		this.sale = sale;
	}
	
	public DailySale(){
		
	}

	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDate()
	{
		return this.date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}

	public int getSale()
	{
		return this.sale;
	}
	public void setSale(int sale)
	{
		this.sale = sale;
	}


}
