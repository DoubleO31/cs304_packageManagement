public class CompanyAddress {
    private long compID;
    private int branch;
    private String caddress;

    public CompanyAddress(long cID, int branch, String caddress) {
        this.compID = cID;
        this.branch = branch;
        this.caddress = caddress;
    }

    public long getCompID() {
        return compID;
    }

    public int getBranch() {
        return branch;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCompID(long compID) {
        this.compID = compID;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }
}
