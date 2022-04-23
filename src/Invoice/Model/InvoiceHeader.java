/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mahmoud0020
 */
public class InvoiceHeader {
    private int Id ;
    private String customerName;
    private Date InvoiceDate;
    private ArrayList<InvoiceDetails> lines;
    public InvoiceHeader() {
    }

    public InvoiceHeader(int Id, String customerName, Date InvoiceDate) {
        this.Id = Id;
        this.customerName = customerName;
        this.InvoiceDate = InvoiceDate;
    }

    public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public ArrayList<InvoiceDetails> getLines() {
        return lines;
    }

    public void setLines(ArrayList<InvoiceDetails> lines) {
        this.lines = lines;
    }
    public float GetTotalInvocie(){
        float total = 0;
        for (int i=0;i<lines.size();i++){
            total = lines.get(i).GetTotal();
        }
        return 0;
    }
}
