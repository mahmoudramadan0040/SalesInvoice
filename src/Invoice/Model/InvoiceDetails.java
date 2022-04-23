/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice.Model;

import java.util.ArrayList;

/**
 *
 * @author mahmoud0020
 */
public class InvoiceDetails {
    private String Item;
    private float Price;
    private int Count;
    // instance of Header Invoice 
    private InvoiceHeader  header ;
    //  invoice  have multiple items  
    
    public InvoiceDetails() {
    }

    public InvoiceDetails(String Item, float Price, int Count, InvoiceHeader header) {
        this.Item = Item;
        this.Price = Price;
        this.Count = Count;
        this.header = header;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    
    // this get total price 
    public float GetTotal (){
        return Price * Count; 
    }

    
}
