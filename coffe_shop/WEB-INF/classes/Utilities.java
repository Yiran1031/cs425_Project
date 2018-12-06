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
            if (session.getAttribute("username") != null) {
                String username = session.getAttribute("username").toString();
                String usertype = session.getAttribute("usertype").toString();
                username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
                if(session.getAttribute("usertype").equals("customer"))
                {
                    result = result
                           +"<li><a href='Cart'><span class='glyphicon'>Cart(CartCount())</span></a></li>"
                           +"<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>"
                           +"<li><a href='Account'><span class='glyphicon'>"+username+"</span></a></li>"
                           +"<li><a href='Review'><span class='glyphicon'>ContactUs</span></a></li>"
                           +"</ul></div></div>";
                }else if(session.getAttribute("usertype").equals("manager"))
                {
                    result = result
                           +"<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>"
                           +"<li><a href='ViewReview'><span class='glyphicon'>Review</span></a></li>"
                           +"<li><a href='DailySale'><span class='glyphicon'>DailySale</span></a></li>"
                           +"<li><a href='ViewSale'><span class='glyphicon'>ViewDiscount</span></a></li>"
                           +"<li><a href='UserList'><span class='glyphicon'>UserList</span></a></li>"
                           +"<li><a href='DataVisualization'><span class='glyphicon'>Chart</span></a></li>"
                           +"<li><a href='Account'><span class='glyphicon'>"+username+"</span></a></li>"
                           +"<li><a href='ManageProduct'><span class='glyphicon'>ManageService</span></a></li>"
                           +"</ul></div></div>";
                }
                //         + "<li><a><span class='glyphicon'>Hello," + username +","+ usertype + "</span></a></li>"
                //         + "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
                //         + "<li><a href='LogOut'><span class='glyphicon'>Logout</span></a></li>";
            } else
            result = result +" <li><a href='History'><span class='glyphicon'>History</span></a></li>"
            +"<li><a href='LoginServlet'><span class='glyphicon'>Login</span></a></li>"
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
        if (session.getAttribute("username")==null)
            return false;
        return true;
    }
}