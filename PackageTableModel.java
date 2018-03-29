import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PackageTableModel extends AbstractTableModel {
    private static final int orderid_COL = 0;
    private static final int packageNo_COL = 1;
    private static final int description_COL = 2;
    private static final int weight_COL = 3;


    private String[] column = { "Order#", "Package No","Description","Weight"};
    private List<Packages> temppackages;

    public PackageTableModel(List<Packages> packages) {
        temppackages = packages;
    }

    @Override
    public int getRowCount() {
        return temppackages.size();
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
        Packages temppackage = temppackages.get(rowIndex);
        switch(columnIndex){
            case orderid_COL:
                return temppackage.getOrderId();
            case packageNo_COL:
                return temppackage.getPackageNo();
            case description_COL:
                return temppackage.getDesc();
            case weight_COL:
                return temppackage.getWeight();
            default:
                return null;
        }
    }
}
