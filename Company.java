public class Company {
    private long compID;
    private String cname;

    public Company(long cID,String cname) {
        this.compID = cID;
        this.cname = cname;
    }

    public long getCompID() {
        return compID;
    }

    public String getCname() {
        return cname;
    }

    public void setCompID(long compID) {
        this.compID = compID;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
