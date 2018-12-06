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

    public static void select_user()
  {
    //HashMap<String,WearableTechnology> hm = new HashMap<String,WearableTechnology>();
    try
    {
        getConnection();
        Statement stmt = conn.createStatement();
        String selectUser = "select * from usera";
        ResultSet  rs = stmt.executeQuery(selectUser);
        while(rs.next())
        {
          System.out.println("username:"+rs.getString("firstname")+","+rs.getString("lastname"));
        }
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
  }
}