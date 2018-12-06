
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


@WebServlet("/CreateCustomer")

public class CreateCustomer extends HttpServlet{
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

        String customer_id = request.getParameter("customer_id");
        String firstname = request.getParameter("customer_firstname");
        String lastname = request.getParameter("customer_lastname");
        String address = request.getParameter("customer_address");
        HashMap<String, Customer> hm=new HashMap<String, Customer>();
        try
        {
                hm = SqlDataStoreUtilities.select_all_customer();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        if(hm.containsKey(customer_id))//check if user is exist
            error_msg = "This account already exist: " + customer_id;
        else
            {
                SqlDataStoreUtilities.insert_Customer(customer_id,firstname,lastname,address);
                error_msg = "New customer "+customer_id+" account has been created.";
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
        pw.print(" <form class='form-horizontal col-sm-offset-3 col-md-offset-3' id='register_form' method='post' action='CreateCustomer'>"+"<div class='col-sm-9 col-md-9'>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-user fa-lg'></i>"
                 +"<input class='form-control required' type='text' placeholder='customer number' name='customer_id' autofocus='autofocus' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-user fa-lg'></i>"
                 +"<input class='form-control required' type='text' placeholder='firstname' name='customer_firstname' autofocus='autofocus' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-user fa-lg'></i>"
                 +"<input class='form-control required' type='text' placeholder='lastname' name='customer_lastname' autofocus='autofocus' required/></div>"
                 +"<div class='form-group'>"
                 +"<i class='fa fa-user fa-lg'></i>"
                 +"<input class='form-control required' type='text' placeholder='address' name='customer_address' autofocus='autofocus' required/></div>"
                 +"<div class='form-group'>"
                 +"<input type='submit' class='btn btn-info pull-left' id='back_btn' value='create'/>"
                 +"</div></div></form>"
                 +"</div></div>");
        utility.printHtml("Footer.html");
    }
}
