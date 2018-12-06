import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
public class SqlDataStoreUtilities
{
	static Connection conn = null;
  	public static void getConnection(){
   	 try{
    	  	Class.forName("org.postgresql.Driver").newInstance();
    	  	conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coffe shop","postgres","cumin");
   		 }
    catch(Exception e){
    		e.printStackTrace();
    	}
 	 }

    public static HashMap<String,User> select_user()
  {
     HashMap<String,User> hm=new HashMap<String,User>();
    try
    {
        getConnection();
        Statement stmt = conn.createStatement();
        String selectUser = "select * from usera";
        ResultSet  rs = stmt.executeQuery(selectUser);
        while(rs.next())
        {
          	User user = new User(rs.getString("user_id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("password"),rs.getInt("priviledge"));
        	hm.put(rs.getString("user_id"), user);
        }
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
    return hm;
  }
  public static HashMap<String,Product> getAllProduct()
  {
  	HashMap<String,Product> hm = new HashMap<String,Product>();
  	try{
  		getConnection();
  		Statement stmt = conn.createStatement();
  		String selectAllProduct = "select * from product";
  		ResultSet rs  = stmt.executeQuery(selectAllProduct);
  		while(rs.next())
  		{
  			Product product = new Product(rs.getString("product_id"),rs.getString("name"),rs.getDouble("price"),rs.getInt("stock_num"),rs.getString("category_type"),rs.getString("image"));
  			hm.put(rs.getString("product_id"),product);
  			//System.out.println("product name:"+product.getName());
  		}
  	}catch(Exception e)
  	{
  		e.printStackTrace();
  	}
  	return hm;
  }

    public static HashMap<String,Customer> getAllCustomer()
  {
  	HashMap<String,Customer> hm = new HashMap<String,Customer>();
  	try{
  		getConnection();
  		Statement stmt = conn.createStatement();
  		String selectAllCustomer = "select * from customer";
  		ResultSet rs  = stmt.executeQuery(selectAllCustomer);
  		while(rs.next())
  		{
  			Customer customer = new Customer(rs.getString("customer_id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("address"),rs.getInt("lifetime_earned_points"),rs.getInt("available_point"));
  			hm.put(rs.getString("customer_id"),customer);
  			//System.out.println("product name:"+product.getName());
  		}
  	}catch(Exception e)
  	{
  		e.printStackTrace();
  	}
  	return hm;
  }

  public static void insert_User(String user_id,String firstname,String lastname,int priviledge,String password)
	{
  	try
  	{
    	getConnection();
    	String insertuser = "INSERT INTO usera(user_id,firstname,lastname,priviledge,password) "
    	+ "VALUES (?,?,?,?,?);";  
        
    	PreparedStatement pst = conn.prepareStatement(insertuser);
    	pst.setString(1,user_id);
    	pst.setString(2,firstname);
    	pst.setString(3,lastname);
    	pst.setInt(4,priviledge);
    	pst.setString(5,password);
    	pst.execute();
  	}
  	catch(Exception e)
  	{
    	e.printStackTrace();
  	} 
	}

	public static Customer search_customer(String customerid)
	{
  	try
  	{
    	getConnection();
    	String insertuser = "SELECT * FROM customer WHERE customer_id=? ";  
        Customer customer = null;
    	PreparedStatement pst = conn.prepareStatement(insertuser);
    	pst.setString(1,customerid);
    	ResultSet rs = pst.executeQuery();
    	if(rs.next())
  		{
 			customer = new Customer(rs.getString("customer_id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("address"),rs.getInt("lifetime_earned_points"),rs.getInt("available_point"));
  		}
  		return customer;
  	}
  	catch(Exception e)
  	{
    	e.printStackTrace();
  	} 
  	return null;
	}
}