/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegeneratormenna;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author menism38
 */
public class SalesInvListener implements ActionListener,ListSelectionListener {

    private InvoiceGeneratorJFrame panel;
    private DateFormat dateCorrectFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public SalesInvListener(InvoiceGeneratorJFrame panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
            case "Create New Invoice":
                showInvoiceTable();
                break;
            case "Delete Invoice":
                RemoveInvoice();
                break;
            case "New Item":
                NewItem();
                break;
            case "Delete Item":
                DeleteItem();
                break;
            case "Load Csv":
                load();
                break;
            case "Save Csv":
                Save();
                break;
            case "Cancel":
                Cancel();
                break;
            case "Create New Inv":
                CreateNewInv();
                break;
            case "Cancel Adding New Item":
                CancelAddingNewItem();
                break;
            case "ConfirmAdding NewInvoice":
                ConfirmCreatingNewInv();
                break;
        }    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    System.out.println("Invoice has beed Selected");
        RowSelected();   
    }
    
        private void RowSelected() 
    {
      int Rindex = panel.getTable1().getSelectedRow();
        if (Rindex >= 0) {
            RightPanelInfo R = panel.getLeftPanelInfo().getNewInvoiceLines().get(Rindex);
            panel.getClient().setText(R.getName());
            panel.getDate().setText(dateCorrectFormat.format(R.getAddingDate()));
            panel.getInvoiceNumber().setText("" + R.getNo());
            panel.getFullSumm().setText("" + R.getCount());
            ArrayList<ItemsInfo> ITEMS = R.getInvoices();
            panel.setNewInvoicesProcessing(new NewInvoicesProcessing(ITEMS));
            panel.getTable2().setModel(panel.getNewInvoicesProcessing());
            panel.getNewInvoicesProcessing().fireTableDataChanged();
        }
    }


    private void showInvoiceTable() {
      panel.setRightPanel(new RightPanel(panel));
        panel.getRightPanel().setVisible(true);    }

    private void RemoveInvoice() {
       int Ind = panel.getTable1().getSelectedRow();
        RightPanelInfo panelinfo = panel.getLeftPanelInfo().getNewInvoiceLines().get(Ind);
        panel.getLeftPanelInfo().getNewInvoiceLines().remove(Ind);
        panel.getLeftPanelInfo().fireTableDataChanged();
        panel.setNewInvoicesProcessing(new NewInvoicesProcessing(new ArrayList<ItemsInfo>()));
        panel.getTable2().setModel(panel.getNewInvoicesProcessing());
        panel.getNewInvoicesProcessing().fireTableDataChanged();
        panel.getClient().setText("");
        panel.getDate().setText("");
        panel.getInvoiceNumber().setText("");
        panel.getFullSumm().setText("");
        ShowInvoice();    }

    private void NewItem() {
    panel.setNewInvoicePopUpDialog(new NewInvoicePopUpDialog(panel));
    panel.getNewInvoicePopUpDialog().setVisible(true);    }

    private void DeleteItem() 
    {
        int RowIndex = panel.getTable2().getSelectedRow();
        ItemsInfo row = panel.getNewInvoicesProcessing().getItemsList().get(RowIndex);
        panel.getNewInvoicesProcessing().getItemsList().remove(RowIndex);
        panel.getNewInvoicesProcessing().fireTableDataChanged();
        panel.getLeftPanelInfo().fireTableDataChanged();
        panel.getFullSumm().setText("" + row.getPanelinfo().getCount());
        ShowInvoice();   
    }

    private void load() {
       JOptionPane.showMessageDialog(panel, "Choose the invoice csv", "Attension!", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser openFile = new JFileChooser();
        int result = openFile.showOpenDialog(panel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = openFile.getSelectedFile();
            try {
                FileReader headerFr = new FileReader(headerFile);
                BufferedReader headerBr = new BufferedReader(headerFr);
                String headerLine = null;

                while ((headerLine = headerBr.readLine()) != null) {
                    String[] headerParts = headerLine.split(",");
                    String invNumStr = headerParts[0];
                    String invDateStr = headerParts[1];
                    String custName = headerParts[2];

                    int invNum = Integer.parseInt(invNumStr);
                    Date invDate = dateCorrectFormat.parse(invDateStr);

                    RightPanelInfo inv = new RightPanelInfo(invNum, custName, invDate);
                    panel.getInvoicesFullInfo().add(inv);
                }

                JOptionPane.showMessageDialog(panel, "Choose the Items CSV", "Attension", JOptionPane.INFORMATION_MESSAGE);
                result = openFile.showOpenDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = openFile.getSelectedFile();
                    BufferedReader linesBr = new BufferedReader(new FileReader(linesFile));
                    String linesLine = null;
                    while ((linesLine = linesBr.readLine()) != null) {
                        String[] lineParts = linesLine.split(",");
                        String invNumStr = lineParts[0];
                        String itemName = lineParts[1];
                        String itemPriceStr = lineParts[2];
                        String itemCountStr = lineParts[3];

                        int invNum = Integer.parseInt(invNumStr);
                        double itemPrice = Double.parseDouble(itemPriceStr);
                        int itemCount = Integer.parseInt(itemCountStr);
                        RightPanelInfo header = investigateForInvoiceByNum(invNum);
                        ItemsInfo invLine = new ItemsInfo(itemName, itemPrice, itemCount, header);
                        header.getInvoices().add(invLine);
                    }
                    panel.setLeftPanelInfo(new LeftPanelInfo(panel.getInvoicesFullInfo()));
                    panel.getTable1().setModel(panel.getLeftPanelInfo());
                    panel.getTable1().validate();
                }
                System.out.println("Check");
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Please enter correct Date Format!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Please enter correct Number Format !\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "File Error!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Read Error!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        ShowInvoice();    }

        private RightPanelInfo investigateForInvoiceByNum(int no) {
        RightPanelInfo RightPanelInfo = null;
        for (RightPanelInfo inv : panel.getInvoicesFullInfo()) {
            if (no == inv.getNo()) {
                RightPanelInfo = inv;
                break;
            }
        }
        return RightPanelInfo;
    }
    private void Save() {
    String panelInfo = "";
        String rows = "";
        for (RightPanelInfo RightPanelInfo : panel.getInvoicesFullInfo()) {
            panelInfo += RightPanelInfo.getInformationInCsvFormat();
            panelInfo += "\n";
            for (ItemsInfo newrow : RightPanelInfo.getInvoices()) {
                rows += newrow.getInformationInCsvFormat();
                rows += "\n";
            }
        }
        JOptionPane.showMessageDialog(panel, "Choose csv invoice File!", "Attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(panel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter hFW = new FileWriter(headerFile);
                hFW.write(panelInfo);
                hFW.flush();
                hFW.close();

                JOptionPane.showMessageDialog(panel, "Choose cvs Items File!", "Attension", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter lFW = new FileWriter(linesFile);
                    lFW.write(rows);
                    lFW.flush();
                    lFW.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(panel, "Data saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void Cancel() {
        panel.getRightPanel().setVisible(false);
        panel.getRightPanel().dispose();
        panel.setRightPanel(null);    }

    private void CreateNewInv() {
       String custName = panel.getRightPanel().getCustomer().getText();
        String strDate = panel.getRightPanel().getDate().getText();
        panel.getRightPanel().setVisible(false);
        panel.getRightPanel().dispose();
        panel.setRightPanel(null);
        try {
            Date invDate = dateCorrectFormat.parse(strDate);
            int invNum = getTheFollowingInvoice();
            RightPanelInfo invoiceHeader = new RightPanelInfo(invNum, custName, invDate);
            panel.getInvoicesFullInfo().add(invoiceHeader);
            panel.getLeftPanelInfo().fireTableDataChanged();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(panel, "Please enter correct date format!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        ShowInvoice();    
    }
   private int getTheFollowingInvoice() {
        int max = 0;
        for (RightPanelInfo RightPanelInfo : panel.getInvoicesFullInfo()) {
            if (RightPanelInfo.getNo() > max) {
                max = RightPanelInfo.getNo();
            }
        }
        return max + 1;
    }
   
    private void CancelAddingNewItem() {
        panel.getNewInvoicePopUpDialog().setVisible(false);
        panel.getNewInvoicePopUpDialog().dispose();
        panel.setNewInvoicePopUpDialog(null);    
    }

    private void ConfirmCreatingNewInv() {
       String itemName = panel.getNewInvoicePopUpDialog().getClient().getText();
        String itemCountStr = panel.getNewInvoicePopUpDialog().getNumberOfItems().getText();
        String itemPriceStr = panel.getNewInvoicePopUpDialog().getPricePerItem().getText();
        panel.getNewInvoicePopUpDialog().setVisible(false);
        panel.getNewInvoicePopUpDialog().dispose();
        panel.setNewInvoicePopUpDialog(null);
        int itemCount = Integer.parseInt(itemCountStr);
        double itemPrice = Double.parseDouble(itemPriceStr);
        int headerIndex = panel.getTable1().getSelectedRow();
        RightPanelInfo invoice = panel.getLeftPanelInfo().getNewInvoiceLines().get(headerIndex);

        ItemsInfo invoiceLine = new ItemsInfo(itemName, itemPrice, itemCount, invoice);
        invoice.newInvoice(invoiceLine);
        panel.getNewInvoicesProcessing().fireTableDataChanged();
        panel.getLeftPanelInfo().fireTableDataChanged();
        panel.getFullSumm().setText("" + invoice.getCount());
        ShowInvoice();
    }


    private void ShowInvoice() 
    
    {
        System.out.println("-----------------------");
        for (RightPanelInfo RightPanelInfo : panel.getInvoicesFullInfo()) {
            System.out.println(RightPanelInfo);
        }
        System.out.println("-----------------------");
    }

}
   