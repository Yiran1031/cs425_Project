import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Product")


public class Product extends HttpServlet{
	String product_id;
	String name;
	Double price;
	int stock_num;
	String image;
	String category_type;
	
	public Product(String product_id,String name,Double price,int stock_num,String category_type,String image){
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.stock_num = stock_num;
		this.image = image;
		this.category_type = category_type;
	}
	
	public Product(){
		
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock_num()
	{
		return this.stock_num;
	}
	public void setStock_num(int stock_num)
	{
		this.stock_num = stock_num;
	}

	public String getCategory()
	{
		return this.category_type;
	}
	public void setCategory(String category_type)
	{
		this.category_type = category_type;
	}
}
