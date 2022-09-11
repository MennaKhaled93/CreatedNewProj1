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
public class NewInvoicesProcessing extends AbstractTableModel{
  
    private  List<ItemsInfo> ItemsList;
    private  DateFormat ItemDate = new SimpleDateFormat("dd-MM-yyyy");
    
    public NewInvoicesProcessing(List<ItemsInfo> ListOfItems) {
        this.ItemsList = ListOfItems;
    }

    public List<ItemsInfo> getItemsList() {
        return ItemsList;
    }
    

    @Override
    public int getRowCount() {
        return ItemsList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public boolean isCellEditable(int ListRow, int ListColumn) {
        return false;
    }
    
    @Override
    public String getColumnName(int Cindex) {
        switch (Cindex) {
            case 0:
                return "Item Name";
            case 1:
                return "Item Price";
            case 2:
                return "Count";
            case 3:
                return "Item Total";
            default:
                return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int Cindex) {
        switch (Cindex) {
            case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Integer.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }
    
    @Override
    public Object getValueAt(int Rindex, int Cindex) {
    ItemsInfo ItemNewLine = ItemsList.get(Rindex);
        
        switch (Cindex) {
            case 0:
                return ItemNewLine.getNewItem();
            case 1:
                return ItemNewLine.getPricePerItem();
            case 2:
                return ItemNewLine.getItemsCount();
            case 3:
                return ItemNewLine.getFullPrice();
            default:
                return "";
            }
    }
        
}

