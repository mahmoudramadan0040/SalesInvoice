/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice.Controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mahmoud0020
 */
public class ItemOperation {
    public  boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public DefaultTableModel CreateItem(JTable Item,ArrayList<String> Datacollection,String index) throws IOException{
        DefaultTableModel model = (DefaultTableModel) Item.getModel();
        int LastRow = Item.getRowCount();
        
        // check if invoice have items before or not 
        if(index != null){
            if(isNumeric(Datacollection.get(1)) && isNumeric(Datacollection.get(2)) ){
                Vector row = new Vector();
                row.add(index.trim());
                row.add(Datacollection.get(0));
                row.add(Datacollection.get(1));
                row.add(Datacollection.get(2));
                float sum =Float.parseFloat(Datacollection.get(1))*Float.parseFloat(Datacollection.get(2));
                row.add(sum);
                model.addRow(row);
                // write the row in the file 
                try{
                    CSVWriter writer = new CSVWriter(new FileWriter("./InvoiceLines.csv", true));
                    String[] result  = (""+row.get(0)+","+row.get(1)+","+row.get(2)+","+row.get(3)).split(",");

                    writer.writeNext(result);
                    writer.close();
                    InvoiceOperation invoice =new InvoiceOperation();
                    return invoice.Refresh();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Can not write line !");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please input Price and count as number ! ");
            }
        }else{
             JOptionPane.showMessageDialog(null, "Please Select Invoice row first ");
        }
        return null;
        
        
    }
    public DefaultTableModel removeItem(JTable Item,String index){
        InvoiceOperation invoice =new InvoiceOperation();
//        System.out.print(Item);
        if(index !=null){
            try{
                DefaultTableModel model =  (DefaultTableModel) Item.getModel();
                int row = Item.getSelectedRow();
                model.removeRow(row);
                int indexInvoice = Integer.valueOf(index.trim());
                CSVReader InvoiceLine = new CSVReader(new FileReader("./InvoiceLines.csv"));
                List<String[]> allLines = InvoiceLine.readAll();
                Vector vector =new Vector();

                for (int i  =0;i<allLines.size();i++){
                    if(indexInvoice == Integer.valueOf(allLines.get(i)[0])){
                        vector.add(i);
                    }
                }
                System.out.print("woow : "+(int)vector.get(row));
                allLines.remove((int)vector.get(row));
                FileWriter sw2 = new FileWriter("./InvoiceLines.csv");
                CSVWriter writer2 = new CSVWriter(sw2);
                writer2.writeAll(allLines);
                writer2.close();
                

                return invoice.Refresh();
            }catch(Exception e){
                System.out.print("Error while remove line");
            }
        
        }else{
            JOptionPane.showMessageDialog(null, "Please Select Invoice row first ");
        }
        return invoice.Refresh();
    }
}
