/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegeneratormenna;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author menism38
 */
public class NewInvoicePopUpDialog extends JDialog {

    private  JTextField Client;
    private  JTextField NumberOfItems;
    private  JTextField PricePerItem;
    private  JLabel NameOfClient;
    private  JLabel ItemsCount;
    private  JLabel PricePerPart;
    private  JButton OkButton;
    private  JButton CancelButton;
 
public NewInvoicePopUpDialog(InvoiceGeneratorJFrame panel) {
        Client = new JTextField(50);
        NameOfClient = new JLabel("Client Name");
        
        NumberOfItems = new JTextField(50);
        ItemsCount = new JLabel("Count");
        
        PricePerItem = new JTextField(50);
        PricePerPart = new JLabel("Price");
        
        OkButton = new JButton("OK");
        CancelButton = new JButton("Cancel");
        
        OkButton.setActionCommand("ConfirmAdding NewInvoice");
        CancelButton.setActionCommand("Cancel Adding New Item");
        
        OkButton.addActionListener(panel.getSalesInvListener());
        CancelButton.addActionListener(panel.getSalesInvListener());
        setLayout(new GridLayout(10, 15));
        
        add(NameOfClient);
        add(Client);
        add(ItemsCount);
        add(NumberOfItems);
        add(PricePerPart);
        add(PricePerItem);
        add(OkButton);
        add(CancelButton);
        
        pack();
    }

     public JTextField getClient() {
        return Client;
    }

    public JTextField getNumberOfItems() {
        return NumberOfItems;
    }

    public JTextField getPricePerItem() {
        return PricePerItem;
    }   
}
