import java.sql.Date;

public class ExistingOrder extends Order {
    private String location;
    private String status;
    public ExistingOrder(long orderid, long companyID, long custID, String type, String senderAddress, String senderName, String receiverAddress, String receiverName, float price,
                         Date dateCreated, Date expectedArrival, String location, String status) {
        super(orderid, companyID,custID, type, senderAddress, senderName, receiverAddress, receiverName, price, dateCreated, expectedArrival);
        this.location = location;
        this.status = status;
    }

    public String getLocation() {
        return this.location;
    }

    public void  setLocation(String loc) {
        this.location = loc;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
