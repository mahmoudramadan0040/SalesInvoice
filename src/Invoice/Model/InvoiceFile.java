/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice.Model;

/**
 *
 * @author mahmoud0020
 */

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class InvoiceFile {
//    private static final String SAMPLE_CSV_FILE_PATH = "./InvoiceHeader.csv";
    // read file csv 
    public List<CSVRecord> ReadCSVFile(String Path) throws IOException{
        try{
            Reader reader = Files.newBufferedReader(Paths.get(Path));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            List<CSVRecord> csvRecords = csvParser.getRecords();
            return csvRecords;
        }catch(Exception e){
           
            System.out.print("can not read file");
        }
        return null ;
    }
    public int getLastsRowIndex(String Path)throws IOException{
        try{
            Reader reader = Files.newBufferedReader(Paths.get(Path));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            
            List<CSVRecord> csvRecords = csvParser.getRecords();
            
            String lastIndex = csvRecords.get(csvRecords.size()).get(0);
//            System.out.print(Integer.parseInt(lastIndex));
            return Integer.parseInt(lastIndex);
        }catch(Exception e){
           
            System.out.print("can not read file");
        }
        return 0;
    }
    public void writeFile(){
        
    }
    // write file csv 
    // save file csv 
    
}
