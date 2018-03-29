import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {

    private static final int orderid_COL = 0;
    private static final int customerid_COL = 1;
    private static final int companyid_COL = 2;
    private static final int type_COL = 3;
    private static final int senderAddress_COL = 4;
    private static final int senderName_COL = 5;
    private static final int receiverAddress_COL = 6;
    private static final int receiverName_COL = 7;
    private static final int price_COL = 8;
    private static final int dateCreated_COL = 9;
    private static final int expectedArrival_COL = 10;

    private String[] column = { "Order#", "Customer ID","Company ID","Delivery Type","Sender Address", "Sender Name",
            "Receiver Address","Receiver Name","Price","Date Created","Expected Arrival"};
    private List<Order> temporders;

    public OrderTableModel(List<Order> orders) {
        temporders = orders;
    }

    @Override
    public int getRowCount() {
        return temporders.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName(int index) {
        return column[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order temporder = temporders.get(rowIndex);
        switch(columnIndex){
            case orderid_COL:
                return temporder.getOrderid();
            case customerid_COL:
                return temporder.getCustID();
            case companyid_COL:
                return temporder.getCompanyID();
            case type_COL:
                return temporder.getType();
            case senderAddress_COL:
                return temporder.getSenderAddress();
            case senderName_COL:
                return temporder.getSenderName();
            case receiverAddress_COL:
                return temporder.getReceiverAddress();
            case receiverName_COL:
                return temporder.getReceiverName();
            case price_COL:
                return temporder.getPrice();
            case dateCreated_COL:
                return temporder.getDateCreated();
            case expectedArrival_COL:
                return temporder.getExpectedArrival();
            default:
                return null;
        }
    }
}
