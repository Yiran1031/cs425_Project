import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

public class Utilities {
    HttpServletRequest req;
    PrintWriter pw;
    String url;
    HttpSession session;

    public Utilities(HttpServletRequest req, PrintWriter pw) {
        this.req = req;
        this.pw = pw;
        this.url = this.getFullURL();
        this.session = req.getSession(true);
    }


    public void printHtml(String file) {
        String result = HtmlToString(file);
        //to print the right navigation in header of username cart and logout etc
        if (file == "Head.html") {
            result = result + "<div id = 'top'><div id ='user'><ul>";
            if (session.getAttribute("user_id") != null) {
                String user_id = session.getAttribute("user_id").toString();
                String usertype = session.getAttribute("usertype").toString();
                user_id = Character.toUpperCase(user_id.charAt(0)) + user_id.substring(1);
                if(session.getAttribute("usertype").equals("0"))
                {
                    result = result
                           +"<li><a href='Cart'><span class='glyphicon'>Cart(CartCount())</span></a></li>"
                           +"<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>"
                           +"<li><a href='Account'><span class='glyphicon'> Hello Employee:"+user_id+"</span></a></li>"
                           +"</ul></div></div>";
                }else if(session.getAttribute("usertype").equals("1"))
                {
                    result = result
                           +"<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>"
                           +"<li><a href='Cart'><span class='glyphicon'>List"+listCount()+")</span></a></li>"
                           +"<li><a href='Registration'><span class='glyphicon'>Create Employee</span></a></li>"
                           +"<li><a href='#'><span class='glyphicon'>DailySale</span></a></li>"
                           +"<li><a href='UserList'><span class='glyphicon'>UserList</span></a></li>"
                           +"<li><a href='DataVisualization'><span class='glyphicon'>Chart</span></a></li>"
                           +"<li><a href='Account'><span class='glyphicon'>Hello Manager:"+user_id+"</span></a></li>"
                           +"<li><a href='ManageProduct'><span class='glyphicon'>ManageService</span></a></li>"
                           +"</ul></div></div>";
                }
                //         + "<li><a><span class='glyphicon'>Hello," + username +","+ usertype + "</span></a></li>"
                //         + "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
                //         + "<li><a href='LogOut'><span class='glyphicon'>Logout</span></a></li>";
            } else
            result = result +"<li><a href='LoginServlet'><span class='glyphicon'>Login</span></a></li>"
            +"</ul></div></div>";
            // result = result + "<li><a href='Cart'><span class='glyphicon'>Cart ("+CartCount() + ")</span></a></li></ul></div></div><div id='page'>";
            pw.print(result);
        }else
            pw.print(result);
    }

    public String getFullURL() {
        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/");
        return url.toString();
    }
    // get the html file and convert into string and return the string.
    public String HtmlToString(String file) {
        String result = null;
        try {
            String webPage = url + file;
            URL url = new URL(webPage);// create a new url object
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            result = sb.toString();
        } catch (Exception e) {
        }
        return result;
    }
    public boolean isLoggedin(){
        if (session.getAttribute("user_id")==null)
            return false;
        return true;
    }
    public void logout(){
        session.removeAttribute("user_id");
        session.removeAttribute("usertype");
    }
    public String getUserid(){
        if (session.getAttribute("user_id")!=null)
            return session.getAttribute("user_id").toString();
        return null;
    }

    public void storeToList(String product_id){
        if(!OrdersHashMap.orders.containsKey(getUserid())){  
            ArrayList<String> arr = new ArrayList<String>();
            OrdersHashMap.orders.put(getUserid(), arr);
        }
        ArrayList<String> orderItems = OrdersHashMap.orders.get(getUserid());
        HashMap<String,Product> allProduct = new HashMap<String,Product> ();
        Product product;
        try{
        allProduct = SqlDataStoreUtilities.getAllProduct();            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        product = allProduct.get(product_id);
        //OrderItem orderitem = new OrderItem(product.getProduct_id());
        orderItems.add(product.getProduct_id());   
    }

    public ArrayList<String> getCustomerOrders(){
        ArrayList<String> order = new ArrayList<String>(); 
        if(OrdersHashMap.orders.containsKey(getUserid()))
            order= OrdersHashMap.orders.get(getUserid());
        return order;
    }
    public int listCount(){
        if(isLoggedin())
        return getCustomerOrders().size();
        return 0;
    }
    public Customer findCustomer(String customerid)
    {
        Customer customer = null;
        customer = SqlDataStoreUtilities.search_customer(customerid);
        return customer;
    }
    public int getTransactionSize(){
        
        HashMap<Integer, ArrayList<Transaction>> transactions = new HashMap<Integer, ArrayList<Transaction>>();
        int size=0;
        try
        {
            transactions =SqlDataStoreUtilities.selectAllTransaction();
                
        }
        catch(Exception e)
        {
            
        }
        for(Map.Entry<Integer, ArrayList<Transaction>> entry : transactions.entrySet()){
                size=entry.getKey();
                System.out.print("Transaction size:"+size);
        }
            
        return size;        
    }

    public void storeTransaction(int transactionid, String customerid, String productid,String date){
        // HashMap<String, ArrayList<Transaction>> transactions = null;
        //     // get the payment details file 
        // try
        // {
        //     transactions=SqlDataStoreUtilities.selectOrder();
        // }
        // catch(Exception e)
        // {
        //     e.printStackTrace();
        // }
        // if(transactions==null)
        // {
        //     transactions = new HashMap<String, ArrayList<Transaction>>();
        // }
        //     // if there exist order id already add it into same list for order id or create a new record with order id
            
        // if(!transactions.containsKey(transactionid)){    
        //     ArrayList<Transaction> arr = new ArrayList<Transaction>();
        //     transactions.put(transactionid, arr);
        // }
        // ArrayList<Transaction> transactionList = transactions.get(transactionid);      
        
        // Transaction transaction = new Transaction(transactionid,customerid,productid);
        // transactionList.add(transaction); 
        try
        {   
            SqlDataStoreUtilities.insertTransaction(transactionid,customerid,productid,date);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   
    }


    public void addPoint(String customer_id, int point)
    {
        SqlDataStoreUtilities.point_add(customer_id,point);
    }
}