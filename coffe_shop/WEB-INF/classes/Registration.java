
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
import java.util.*;














@WebServlet("/Registration")

public class Registration extends HttpServlet{
    private String error_msg;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayRegistration(request, response, pw, false);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

        String user_id = request.getParameter("user_id");
        String firstname = request.getParameter("user_firstname");
        String lastname = request.getParameter("user_lastname");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String usertype = request.getParameter("usertype");
        if(!password.equals(repassword))
        {
            error_msg = "Passwords does't match";
        }else
            {
                HashMap<String, User> hm=new HashMap<String, User>();

                try
                {
                    hm = SqlDataStoreUtilities.select_user();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                if(hm.containsKey(user_id))//check if user is exist
                    error_msg = "This account already exist: " + user_id;
                else
                    {
                        SqlDataStoreUtilities.insert_User(user_id,firstname,lastname,Integer.parseInt(usertype),password);
                        HttpSession session = request.getSession(true);
                        error_msg = "New user "+user_id+" account has been created.";
                    }
            }
        displayRegistration(request, response, pw, true);
    }



protected void displayRegistration(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, boolean error) throws ServletException, IOException {
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Head.html");
        utility.printHtml("Title.html");
        pw.print("<div class = 'content'><div class='login'>");
        pw.print("<a style='font-size: 24px;'>create a new user account</a><br><br><br>");
        if (error)
            pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
        pw.print(" <form class='form-horizontal col-sm-offset-3 col-md-offset-3' id='register_form' method='post' action='Registration'>"+"<div class='col-sm-9 col-md-9'>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-user fa-lg'></i>"
                 +"<input class='form-control required' type='text' placeholder='account number' name='user_id' autofocus='autofocus' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-user fa-lg'></i>"
                 +"<input class='form-control required' type='text' placeholder='firstname' name='user_firstname' autofocus='autofocus' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-user fa-lg'></i>"
                 +"<input class='form-control required' type='text' placeholder='lastname' name='user_lastname' autofocus='autofocus' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-lock fa-lg'></i>"
                 +"<input class='form-control required' type='password' placeholder='password' id='register_password' name='password' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-lock fa-lg'></i>"
                 +"<input class='form-control required' type='password' placeholder='RePassword' id='register_password' name='repassword' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-envelope fa-lg'></i>"
                 +"<select class='form-control' name='usertype'>"
                 +"<option value='0'>user</option><option value='1'>manager</option>"
                 +"</select>"
                 +"</div>"
                 +"<div class='form-group'>"
                 +"<input type='submit' class='btn btn-info pull-left' id='back_btn' value='registration'/>"
                 +"</div></div></form>"
                 +"</div></div>");
        utility.printHtml("Footer.html");
    }
}
