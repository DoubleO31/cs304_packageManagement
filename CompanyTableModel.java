import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

public class CompanyTableModel extends AbstractTableModel{
    private static final int companyIDCol = 0;
    private static final int companyNameCol = 1;
    private static final int branchCol = 2;
    private static final int addressCol = 3;

    private String[] column = {"Company ID","Company Name", "Branch Number", "Company Branch Address"};
    private List<Company>  comps;
    private Map<Long,CompanyAddress> addrs;

    public CompanyTableModel(List<Company> c, Map<Long,CompanyAddress> a) {
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
        Company comp = comps.get(columnIndex);
        CompanyAddress addr = addrs.get(comp.getCompID());
        switch (columnIndex) {
            case companyIDCol:
                return comp.getCompID();
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
