
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;













@WebServlet("/Registration")

public class Registration extends HttpServlet{
    private String error_msg;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       SqlDataStoreUtilities.select_user();
    }
    // protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    //     response.setContentType("text/html");
    //     PrintWriter pw = response.getWriter();
    //     displayRegistration(request, response, pw, false);
    // }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        SqlDataStoreUtilities.select_user();
        // response.setContentType("text/html");
        // PrintWriter pw = response.getWriter();
        // Utilities utility = new Utilities(request, pw);

        // String username = request.getParameter("username");
        // String password = request.getParameter("password");
        // String repassword = request.getParameter("repassword");
        // String usertype = request.getParameter("usertype");
        // // no customer log in
        // if(!utility.isLoggedin())
        //      usertype = request.getParameter("usertype");
        // if(!password.equals(repassword))
        // {
        //     error_msg = "Passwords does't match";
        // }else
        //     {
        //         HashMap<String, User> hm=new HashMap<String, User>();
        //         //String TOMCAT_HOME = System.getProperty("catalina.home");

        //         try
        //         {
        //             // FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\csj\\UserDetails.txt"));
        //             // ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        //             // hm= (HashMap)objectInputStream.readObject();
        //             hm = MySqlDataStoreUtilities.selectUser();
        //         }
        //         catch(Exception e)
        //         {
        //         }

        //         if(hm.containsKey(username))//check if user is exist
        //             error_msg = "Username already exist as " + usertype;
        //         else
        //             {
        //                 User user = new User(username,password,usertype);
        //                 hm.put(username,user);
        //                 System.out.println(username+","+password+","+repassword+","+usertype);
        //                 MySqlDataStoreUtilities.insertUser(username,password,repassword,usertype);
        //                 HttpSession session = request.getSession(true);
        //                 session.setAttribute("login_msg","Your"+usertype+"account has been created. Please login");
        //                 if(!utility.isLoggedin())
        //                 {
        //                   response.sendRedirect("LoginServlet"); return;
        //                 }else{
        //                   response.sendRedirect("Account"); return;
        //                 }
        //                 // FileOutputStream fileOutputStream = new FileOutputStream(TOMCAT_HOME+"\\webapps\\csj\\UserDetails.txt");
        //                 // ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        //                 // objectOutputStream.writeObject(hm);
        //                 // objectOutputStream.flush();
        //                 // objectOutputStream.close();
        //                 // fileOutputStream.close();
        //                 // HttpSession session = request.getSession(true);
        //                 // session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
        //                 // if(!utility.isLoggedin()){
        //                 //     response.sendRedirect("LoginServlet");
        //                 //     return;
        //                 // }
        //             }
        //     }
        // if(utility.isLoggedin()){
        //     HttpSession session = request.getSession(true);
        //     session.setAttribute("login_msg",error_msg);
        //     return;
        // }
        // displayRegistration(request, response, pw, true);
    }



    // protected void displayRegistration(HttpServletRequest request,
    //                                    HttpServletResponse response, PrintWriter pw, boolean error)
    //         throws ServletException, IOException {
    //     Utilities utility = new Utilities(request, pw);
    //     utility.printHtml("Header.html");
    //     pw.print("<div class='post' style='float: none; width: 100%'>");
    //     pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
    //             + "<div class='entry'>"
    //             + "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
    //     if (error)
    //         pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
    //     pw.print("<form method='post' action='Registration'>"
    //             + "<table style='width:100%'><tr><td>"
    //             + "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
    //             + "</td></tr><tr><td>"
    //             + "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
    //             + "</td></tr><tr><td>"
    //             + "<h3>Re-Password</h3></td><td><input type='password' name='repassword' value='' class='input' required></input>"
    //             + "</td></tr><tr><td>"
    //             + "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='storemanager'>Store Manager</option><option value='salesman'>Salesman</option></select>"
    //             + "</td></tr></table>"
    //             + "<input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
    //             + "</form>" + "</div></div></div>");
    //     utility.printHtml("Footer.html");
    // }
}
