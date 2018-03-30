import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

public class CompanyTableModel extends AbstractTableModel{
    private static final int companyIDCol = 0;
    private static final int companyNameCol = 1;
    private static final int branchCol = 2;
    private static final int addressCol = 3;

    private String[] column = {"Company ID","Company Name", "Branch Number", "Company Branch Address"};
    private Map<Long,Company> comps;
    private List<CompanyAddress> addrs;

    public CompanyTableModel(Map<Long,Company> c, List<CompanyAddress> a) {
        comps = c;
        addrs = a;
    }
    @Override
    public int getRowCount() {
        return comps.size();
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

        CompanyAddress addr = addrs.get(rowIndex);
        Company comp = comps.get(addr.getCompID());
        switch (columnIndex) {
            case companyIDCol:
                return addr.getCompID();
            case companyNameCol:
                return comp.getCname();
            case branchCol:
                return addr.getBranch();
            case addressCol:
                return addr.getCaddress();
            default:
                return null;
        }
    }
}
