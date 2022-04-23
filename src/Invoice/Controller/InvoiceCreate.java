/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice.Controller;

import Invoice.Model.InvoiceFile;
import Invoice.Model.InvoiceHeader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author mahmoud0020
 */
public class InvoiceCreate {
    public boolean checkDate(String date){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
          dateFormat.parse(date);
          return true;
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Please input date like this dd/MM/yyyy");
        }
        return false;
    }
    public void Create(JTable Invoice , ArrayList<String> Datacollection ) throws ParseException, IOException{

        DefaultTableModel model = (DefaultTableModel) Invoice.getModel();
        int LastRow = Invoice.getRowCount();
        if(LastRow !=0 ){
            float index =  Float.parseFloat((String) Invoice.getValueAt(LastRow-1, 0));
            boolean date =checkDate(Datacollection.get(1)); 
            if(date && !Datacollection.get(0).isEmpty()){
                System.out.print("not empty ");
                Vector row = new Vector();
                row.add((int)(index+1.0));
                row.add(Datacollection.get(1));
                row.add(Datacollection.get(0));
                row.add(0.0);
                model.addRow(row);
                // write the row in the file 
                try{
                    CSVWriter writer = new CSVWriter(new FileWriter("./InvoiceHeader.csv", true));
//                    CSVReader InvoiceHead = new CSVReader(new FileReader("./InvoiceHeader.csv"));
                    String[] result  = (""+row.get(0)+","+row.get(1)+","+row.get(2)).split(",");
                    
                    writer.writeNext(result);
                    writer.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Can not write line !");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please input date and Customer name ! ");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please load file first ! ");
        }
              
       
    }
    
    
     
}
