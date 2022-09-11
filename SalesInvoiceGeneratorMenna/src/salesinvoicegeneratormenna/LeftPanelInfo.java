/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegeneratormenna;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author menism38
 */
public class LeftPanelInfo  extends AbstractTableModel{


    private  List<RightPanelInfo> NewInvoiceLines;
    private  DateFormat dateFormatStandard = new SimpleDateFormat("dd-MM-yyyy");
       
    public LeftPanelInfo(List<RightPanelInfo> NewInvoiceRows) {
        this.NewInvoiceLines = NewInvoiceRows;
    }

    public List<RightPanelInfo> getNewInvoiceLines() {
        return NewInvoiceLines;
    }
    
    @Override
    public int getRowCount() {
        return NewInvoiceLines.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
  @Override
    public String getColumnName(int Cindex) {
        switch (Cindex) {
            case 0:
                return "No.";
            case 1:
                return "Date";
            case 2:
                return "Customer";
            case 3:
                return "Total";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int Cindex) {
        switch (Cindex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int Rindex, int Cindex) {
        return false;
    }
    @Override
    public Object getValueAt(int Rindex, int Cindex) {
   RightPanelInfo NewInvoiceLine = NewInvoiceLines.get(Rindex);
        
        switch (Cindex) {
            case 0:
                return NewInvoiceLine.getNo();
            case 1:
                return dateFormatStandard.format(NewInvoiceLine.getAddingDate());
              
            case 2:
                return NewInvoiceLine.getName();  
            case 3:
                return NewInvoiceLine.getCount();
            default:
                return "";
        }
        
    }
}
    
