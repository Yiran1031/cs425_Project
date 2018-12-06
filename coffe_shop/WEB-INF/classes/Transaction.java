import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

public class Transaction implements Serializable{
    private int transaction_id;
    private String customer_id;
    private String product_id;

    public Transaction(int transaction_id,String customer_id,String product_id)
    {
        this.transaction_id = transaction_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
    }

    public Transaction()
    {

    }
    public int gerTransaction_id()
    {
        return this.transaction_id;
    }
    public void setTransaction_id(int transaction_id)
    {
        this.transaction_id = transaction_id;
    }

    public String getCustomer_id()
    {
        return this.customer_id;
    }

    public void setCustomer_id(String customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getProduct_id()
    {
        return this.product_id;
    }

    public void setProduct_id(String product_id)
    {
        this.product_id = product_id;
    }

}
