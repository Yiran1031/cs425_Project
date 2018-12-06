import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

public class Customer implements Serializable{
    private String customer_id;
    private String firstname;
    private String lastname;
    private String address;
    private int lifetime_point;
    private int avaiable_point;

    public Customer(String customer_id,String firstname,String lastname,String address,int lifetime_point,int avaiable_point)
    {
        this.customer_id = customer_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.lifetime_point = lifetime_point;
        this.avaiable_point = avaiable_point;
    }
    public Customer()
    {

    }
    public void setCustomerId(String customerid)
    {
        this.customer_id = customerid;
    }
    public String getCustomerId()
    {
        return this.customer_id;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getFirstname()
    {
        return this.firstname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public String getLastname()
    {
        return this.lastname;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return this.address;
    }

    public void setLifeTimePoint(int lifetime_point)
    {
        this.lifetime_point = lifetime_point;
    }
    public int getLifeTimePoint()
    {
        return this.lifetime_point;
    }

    public void setAvaiablePoint(int avaiable_point)
    {
        this.avaiable_point = avaiable_point;
    }
    public int getAvaiablePoint()
    {
        return this.avaiable_point;
    }

}
