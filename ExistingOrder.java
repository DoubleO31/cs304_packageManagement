package cs304_packageManagement;

import java.sql.Date;

public class ExistingOrder extends Order {
    private String location;
    private String status;
    private Date dateUpdated;
    private String instance;
    public ExistingOrder(long orderid, long companyID, long custID, String type, String senderAddress, String senderName, String receiverAddress, String receiverName, float price,
                         Date dateCreated, Date expectedArrival, String location, String status, Date updateDate, String instance) {
        super(orderid, companyID,custID, type, senderAddress, senderName, receiverAddress, receiverName, price, dateCreated, expectedArrival);
        this.location = location;
        this.status = status;
        this.dateUpdated = updateDate;
        this.instance = instance;
    }

    public String getLocation() {
        return this.location;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public String getInstance() {
        return instance;
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

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
