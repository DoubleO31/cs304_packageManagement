import java.util.Date;

public class Order {
    private String orderid;
    private String senderAddress;
    private String senderName;
    private String receiverAddress;
    private String receiverName;
    private float price;
    private Date dateCreated;
    private Date expectedArrival;


    public Order(String orderid, String senderAddress, String senderName, String receiverAddress, String receiverName,
                 float price, Date dateCreated, Date expectedArrival){
        this.orderid = orderid;
        this.senderAddress = senderAddress;
        this.senderName = senderName;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.price = price;
        this.dateCreated = dateCreated;
        this.expectedArrival = expectedArrival;
        }


    public String getOrderid() {
        return orderid;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public String getSenderName() {
        return senderName;
    }

    public float getPrice() {
        return price;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getExpectedArrival() {
        return expectedArrival;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setExpectedArrival(Date expectedArrival) {
        this.expectedArrival = expectedArrival;
    }
}
