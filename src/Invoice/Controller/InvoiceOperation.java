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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author mahmoud0020
 */
public class InvoiceOperation extends JFrame{

    public InvoiceOperation() {
    }
    // handel load invoices table 
    public   List<CSVRecord> csvRecordLines;
    public   List<CSVRecord> csvRecordHeader;
    public static String FilePathHeader;
    public static String FilePathLines;
    public List<CSVRecord> getCsvRecordLines() {
        return csvRecordLines;
    }

    public void setCsvRecordLines(List<CSVRecord> csvRecordLines) {
        this.csvRecordLines = csvRecordLines;
    }
    public Boolean ValidExtension(String path){
        String extension = FilenameUtils.getExtension(path);
        if("csv".equals(extension)){
            
            return true;
        }
        System.out.print("fails "+ extension);
        return false;
        
    }
    public String ExcelChooser(String msg ,String Path){
        JOptionPane.showMessageDialog(null, msg);
        String defaultCurrentDirectoryPath =Path;
        JFileChooser CSVFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        CSVFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter(null,"csv");
        CSVFileChooser.setFileFilter(fnef);
        int CSV_Chooser = CSVFileChooser.showOpenDialog(null);
        if(CSV_Chooser == JFileChooser.APPROVE_OPTION){
            // read CSV file 
              
            String FilePath = CSVFileChooser.getSelectedFile().getAbsolutePath();
            // check the extension of file is csv or not 
            boolean valid =ValidExtension(FilePath);
            if(!valid){
                JOptionPane.showMessageDialog(null, "Please selected folder with extension CSV");
                return null;
            }
            return FilePath;
        }
        return null;
    }
    public DefaultTableModel Refresh(){
        DefaultTableModel csv_data =new DefaultTableModel();
        try{
                // read CSV file 
                // retrived data from csv to jtable 
                InvoiceFile file =new InvoiceFile();
                csvRecordHeader = file.ReadCSVFile(FilePathHeader);
                csvRecordLines = file.ReadCSVFile(FilePathLines);
                setCsvRecordLines(csvRecordLines);
                csv_data.addColumn("id");
                csv_data.addColumn("Date");
                csv_data.addColumn("Customer");
                csv_data.addColumn("Total");
                float count =0;
                for (int i =0 ;i< csvRecordHeader.size();i++){
                    for(int j=0 ;j <csvRecordLines.size();j++){
                        String IndexHeader = csvRecordHeader.get(i).get(0).trim();
                        int result_1 =Integer.valueOf(IndexHeader);
                        String indexLines =csvRecordLines.get(j).get(0);
                        int result_2 = Integer.valueOf(indexLines);
                        if(result_1 == result_2 ){
                            String temp1 =csvRecordLines.get(j).get(3).trim();
                            String temp2 =csvRecordLines.get(j).get(2);
                            float val_1 = Float.parseFloat(temp1);
                            float val_2 =  Float.parseFloat(temp2);
                            count =count+ val_1*val_2 ;
                            
                        }
                    }
                    Vector row = new Vector();
                    row.add(csvRecordHeader.get(i).get(0));
                    row.add(csvRecordHeader.get(i).get(1));
                    row.add(csvRecordHeader.get(i).get(2));
                    
                    row.add(String.valueOf(count));
                    
                    csv_data.addRow(row);
                    count =0;
                }

//                JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
                return csv_data;
            }catch(Exception e){
                System.out.print("can not read file ");
            }
        return null;
    }
    public DefaultTableModel loadInvoices() throws IOException{
         FilePathHeader = ExcelChooser("Please selected HeadInvoice File with extension CSV","./InvoiceHeader.csv");
         FilePathLines =  ExcelChooser("Please selected LinesInvoice File with extension CSV","./InvoiceLines.csv");
        
        DefaultTableModel csv_data =new DefaultTableModel();
        if (FilePathHeader != null && FilePathLines != null ) {
            try{
                // read CSV file 
                // retrived data from csv to jtable 
                InvoiceFile file =new InvoiceFile();
                csvRecordHeader = file.ReadCSVFile(FilePathHeader);
                csvRecordLines = file.ReadCSVFile(FilePathLines);
                setCsvRecordLines(csvRecordLines);
                csv_data.addColumn("id");
                csv_data.addColumn("Date");
                csv_data.addColumn("Customer");
                csv_data.addColumn("Total");
                float count =0;
                for (int i =0 ;i< csvRecordHeader.size();i++){
                    for(int j=0 ;j <csvRecordLines.size();j++){
                        String IndexHeader = csvRecordHeader.get(i).get(0).trim();
                        int result_1 =Integer.valueOf(IndexHeader);
                        String indexLines =csvRecordLines.get(j).get(0);
                        int result_2 = Integer.valueOf(indexLines);
                        if(result_1 == result_2 ){
                            String temp1 =csvRecordLines.get(j).get(3).trim();
                            String temp2 =csvRecordLines.get(j).get(2);
                            float val_1 = Float.parseFloat(temp1);
                            float val_2 =  Float.parseFloat(temp2);
                            count =count+ val_1*val_2 ;
                            
                        }
                    }
                    Vector row = new Vector();
                    row.add(csvRecordHeader.get(i).get(0));
                    row.add(csvRecordHeader.get(i).get(1));
                    row.add(csvRecordHeader.get(i).get(2));
                    
                    row.add(String.valueOf(count));
                    
                    csv_data.addRow(row);
                    count =0;
                }

                JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
                return csv_data;
            }catch(Exception e){
                System.out.print("can not read file ");
            }
        }
        return null;
    }
    public DefaultTableModel SelectInvoice(JTable table) throws FileNotFoundException, IOException{
        DefaultTableModel model = new DefaultTableModel();
        // set header of lines
        model.addColumn("No");
        model.addColumn("Item Name");
        model.addColumn("Item Price");
        model.addColumn("Count");
        model.addColumn("Item Total");
        int row = table.getSelectedRow();
        System.out.print("\n the new row: "+row+"\n");
        System.out.print("\n oops : "+table.getModel().getValueAt(row, 0).toString()+"\n");
        System.out.print(Integer.parseInt( table.getModel().getValueAt(row, 0).toString()));
//        System.out.print("\n this line is problem"+"\n");
        InvoiceFile file =new InvoiceFile();
        System.out.print(FilePathLines);
        csvRecordLines = file.ReadCSVFile(FilePathLines);
        for(int j=0 ;j <csvRecordLines.size();j++){

            Vector Row = new Vector();
            String indexLines =csvRecordLines.get(j).get(0);
            
            
            int result = Integer.valueOf(indexLines);
            int result2 = Integer.parseInt( table.getModel().getValueAt(row, 0).toString());
            System.out.print("\n result : "+result+"\n");
            System.out.print("\n second "+Integer.parseInt( table.getModel().getValueAt(row, 0).toString())+"\n");
            if(result == result2  ){
                System.out.print("\n this line is problem"+"\n");
                Row.add(csvRecordLines.get(j).get(0));
                Row.add(csvRecordLines.get(j).get(1));
                Row.add(csvRecordLines.get(j).get(2));
                Row.add(csvRecordLines.get(j).get(3));
                String temp1 =csvRecordLines.get(j).get(3).trim();
                String temp2 =csvRecordLines.get(j).get(2);
                float val_1 = Float.parseFloat(temp1);
                float val_2 =  Float.parseFloat(temp2);
                Row.add(val_1*val_2);
                model.addRow(Row);
//                csvRecordLines.remove(j);
            }
        }
        
        return model;
    }

    
    
    public void SaveStatus(JTable Invoice) throws FileNotFoundException, IOException{
//        CSVReader InvoiceHead = new CSVReader(new FileReader("./InvoiceHeader.csv"));
        TableModel model = Invoice.getModel();
        FileWriter csv = new FileWriter(new File("./InvoiceHeader.csv"));
        if(model.getRowCount()>0){
            System.out.print(model.getRowCount());
            System.out.print(model.getColumnCount());
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount()-1; j++) {
                    csv.write(model.getValueAt(i, j) + ",");
                }
                System.out.print("row : "+ i+"\n");
            csv.write("\n");
            
            }
            csv.close();
            JOptionPane.showMessageDialog(null, "File Saved Confirmed ! ");
        }else{
            JOptionPane.showMessageDialog(null, "Can not Save State because no record found !");
        }
    }
    public DefaultTableModel RemoveInvoice(JTable Invoice,JTable Item){

        // remove from the file 
        
        try{
            DefaultTableModel model =  (DefaultTableModel) Invoice.getModel();
            DefaultTableModel model2 =  (DefaultTableModel) Item.getModel();
            int row = Invoice.getSelectedRow();
            
            CSVReader InvoiceHead = new CSVReader(new FileReader("./InvoiceHeader.csv"));
            CSVReader InvoiceLine = new CSVReader(new FileReader("./InvoiceLines.csv"));
            List<String[]> allInvoice = InvoiceHead.readAll();
            List<String[]> allLines = InvoiceLine.readAll();
            Vector vec =new Vector();
            for(int j=0 ;j <allLines.size();j++){
                String indexLines =allLines.get(j)[0];
                int result = Integer.valueOf(indexLines);
                if(result == Integer.parseInt(Invoice.getModel().getValueAt(row, 0).toString()) ){
                    vec.add(j);
                }
            }   
            model.removeRow(row);
            allInvoice.remove(row);
            
            for(int i = vec.size()-1; i>=0;i-- ){
               System.out.print("the index line is : "+vec.get(i)+"\n");
               allLines.remove((int)vec.get(i));
//               csvRecordLines.remove((int)vec.get(i)); 
            }
            for (int i =Item.getRowCount()-1; i>=0;i--){
                model2.removeRow(i);
            }
            
            
            FileWriter sw = new FileWriter("./InvoiceHeader.csv");
            FileWriter sw2 = new FileWriter("./InvoiceLines.csv");
            CSVWriter writer1 = new CSVWriter(sw);
            CSVWriter writer2 = new CSVWriter(sw2);
            writer1.writeAll(allInvoice);
            writer2.writeAll(allLines);
            writer1.close();
            writer2.close();
        }catch(Exception e){
            System.out.print("can not write file ");
        }
        
        return null;
    }
    public Vector showSelectInvoice(JTable table){
        List<String> result = null ;
        DefaultTableModel model =  (DefaultTableModel) table.getModel();
        
        Vector Row = new Vector();
        int row = table.getSelectedRow();
        return (Vector) model.getDataVector().get(row);   
    }
}
