public class Packages {
    long orderId;
    int packageNo;
    String desc;
    double weight;


    public Packages(Long orderId, int packageNO, String desc, double weight){
        this.orderId = orderId;
        this.packageNo = packageNO;
        this.desc = desc;
        this.weight = weight;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getPackageNo() {
        return packageNo;
    }

    public String getDesc() {
        return desc;
    }

    public double getWeight() {
        return weight;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setPackageNo(int packageNo) {
        this.packageNo = packageNo;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
