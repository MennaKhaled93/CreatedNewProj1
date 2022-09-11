/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegeneratormenna;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author menism38
 */
public class RightPanelInfo {
 
 private  int No;
    private  String Name;
    private  Date AddingDate;
    private ArrayList<ItemsInfo> invoices;  

public RightPanelInfo(int Number, String Client, Date todayDate) {
        this.No = Number;
        this.Name = Client;
        this.AddingDate = todayDate;
    }
    public Date getAddingDate() {
        return AddingDate;
    }

    public void setAddingDate(Date AddingDate) {
        this.AddingDate = AddingDate;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int No) {
        this.No = No;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

 @Override
    public String toString() {
        String str = "RowInfo{" + "invoicenum=" + No + ", Client=" + Name + ", date=" + AddingDate + '}';
        for (ItemsInfo line : getInvoices()) {
            str += "\n\t" + line;
        }
        return str;
    }

    public ArrayList<ItemsInfo> getInvoices() {
        if (invoices == null)
            invoices = new ArrayList<>(); 
        return invoices;
    }

    public void setInvoices(ArrayList<ItemsInfo> invoices) {
        this.invoices = invoices;
    }

    public double getCount() {
        double finalcount = 0.0;
        for (ItemsInfo line : getInvoices()) {
            finalcount += line.getFullPrice();
        }
        return finalcount;
    }
    
    public void newInvoice(ItemsInfo Row) {
        getInvoices().add(Row);
    }
    
    public String getInformationInCsvFormat() {
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getNo() + "," + dateformat.format(getAddingDate()) + "," + getName();
    }
    
}
