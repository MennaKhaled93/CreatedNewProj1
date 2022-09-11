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
public class RightPanel extends JDialog {
 

    private  JTextField Customer;
    private  JTextField Date;
    private  JLabel CustomerName;
    private  JLabel InvoiceDate;
    private  JButton NewItems;
    private  JButton Delete;   
    
public RightPanel(InvoiceGeneratorJFrame panel) {
        CustomerName = new JLabel("Customer Name");
        Customer = new JTextField(50);
        InvoiceDate = new JLabel("Invoice Date");
        Date = new JTextField(50);
        NewItems = new JButton("OK");
        Delete = new JButton("Cancel");
        
        NewItems.setActionCommand("Create New Inv");
        Delete.setActionCommand("Cancel");
        
        NewItems.addActionListener(panel.getSalesInvListener());
        Delete.addActionListener(panel.getSalesInvListener());

        setLayout(new GridLayout(10, 15));
        
        add(InvoiceDate);
        add(Date);
        add(CustomerName);
        add(Customer);
        add(NewItems);
        add(Delete);
        
        pack();
        
    }   

    public JTextField getCustomer() {
        return Customer;
    }

    public JTextField getDate() {
        return Date;
    }
    
}

