import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();


        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        HashMap<String, User> hm=new HashMap<String, User>();

        try
        {   
            hm=SqlDataStoreUtilities.select_user();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        User user = hm.get(user_id);
        if(user!=null)
        {
         String user_password = user.getPassword();
         if (password.equals(user_password) && usertype.equals(user.getUsertype())) 
            {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_id", user.getId());
            session.setAttribute("usertype", user.getUsertype());
            System.out.println(user.getUsertype());
            response.sendRedirect("Home");
            return;
            
            }
        }
        displayLogin(request, response, pw, true);
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayLogin(request, response, pw, false);
    }


    /*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
    protected void displayLogin(HttpServletRequest request,
            HttpServletResponse response, PrintWriter pw, boolean error)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Head.html");
        utility.printHtml("Title.html");
        pw.print("<div class = 'content'><div class = 'login'>");
        pw.print("<h4 class='form-title'>Log in to your account</h4></br>");
        if (error)
            pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
        HttpSession session = request.getSession(true);
        if(session.getAttribute("login_msg")!=null){            
            pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4></br>");
            session.removeAttribute("login_msg");
        }
        pw.print(" <form class='form-horizontal col-sm-offset-3 col-md-offset-3' id='register_form' name = 'login'>"+"<div class='col-sm-9 col-md-9'>"
                        +"<div class='form-group'>"
                        +"<i class='fa fa-user fa-lg'></i>"
                        +"<input class='form-control required' type='text' placeholder='user-id' name='user_id' autofocus='autofocus'required/>"
                        +"</div><div class='form-group'>"
                        +"<i class='fa fa-lock fa-lg'></i>"
                        +"<input class='form-control required' type='password' placeholder='Password' name='password' required/></div>"
                        +"<div class='form-group'>"
                        +"<i class='fa fa-envelope fa-lg'></i>"
                        +"<select class='form-control' name='usertype'>"
                        +"<option value='0'>employee</option><option value='1'>manager</option>"
                        +"</select>"
                        +"</div>"
                        +"<div class='form-group'>"
                        +"<input type='button' onclick='Login()' class='btn btn-success pull-right' value='Sign in '/>"
                        +"</div></div></form></div></div></div></div></div>");
        utility.printHtml("Footer.html");
    }

}
