/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegeneratormenna;

/**
 *
 * @author menism38
 */
public class ItemsInfo {
  
    private String NewItem;
    private double pricePerItem;
    private int ItemsCount;
    private RightPanelInfo panelinfo;  
    
     public ItemsInfo(String NameOfNewItem, double PricePerItem, int count, RightPanelInfo panelFullInformation) {
        this.NewItem = NameOfNewItem;
        this.pricePerItem = PricePerItem;
        this.ItemsCount = count;
        this.panelinfo = panelFullInformation;
    }
       
public String getNewItem() {
        return NewItem;
    }

    public void setNewItem(String NewItem) {
        this.NewItem = NewItem;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public int getItemsCount() {
        return ItemsCount;
    }

    public void setItemsCount(int ItemsCount) {
        this.ItemsCount = ItemsCount;
    }

    public RightPanelInfo getPanelinfo() {
        return panelinfo;
    }

    public void setPanelinfo(RightPanelInfo panelinfo) {
        this.panelinfo = panelinfo;
    }

    @Override
    public String toString() {
        return "SalesInvoiceGenerator{" + "NameOfItem=" + NewItem + ", PriceOfEachItem=" + pricePerItem + ", CountOfItems=" + ItemsCount + '}';
    }
    
    public double getFullPrice() {
        return ItemsCount * pricePerItem;
    }
    
    public String getInformationInCsvFormat() {
        return "" + getPanelinfo().getNo() + "," + getNewItem() + "," + getPricePerItem() + "," + getItemsCount();
    }
}
