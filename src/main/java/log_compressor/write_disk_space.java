/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log_compressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Zan_Wang
 */
public class write_disk_space {

    public static void write_disk_space(HashMap<String, ArrayList<String>> map, List<String> server_list) throws FileNotFoundException, IOException {
        File myFile = new File("D:\\log\\log_output.xlsx");
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        HashMap<String, String> res = new HashMap<String, String>();
        ArrayList<String> key_set = new ArrayList<String>();
        ArrayList<String> free_space = new ArrayList<String>();
        int rownum = mySheet.getLastRowNum() + 1;
        Row row1 = mySheet.createRow(rownum++);
        
       
        Cell cell2 = row1.createCell(0);
        Date date = new Date();
        cell2.setCellValue(date.toString());
        int i = 0;
        for (String key : server_list) {
            free_space.clear();
            ArrayList<String> disk_free = map.get(key);

            for (String df : disk_free) {
                
                if (!df.equals("need manual check")) {
                    int free_position1 = df.lastIndexOf("GB");
                    int free_position2 = df.lastIndexOf("free");
                    String disk = df.substring(0, 1);
                    String key_disk = key + ":" + disk;

                    String free_space_percent = df.substring(free_position1 + 3, free_position2 - 1);
                    free_space.add(free_space_percent);
                    res.put(key_disk, free_space_percent);
                    key_set.add(key_disk);
                } else {

                    res.put(key, "need manual check");
                    key_set.add(key);
                }
            }

            /*
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                String res_key = (String) pair.getKey();
                String res_res = (String) pair.getValue();
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(res_key);
                cellnum = cellnum + 2;
                Cell cell1 = row.createCell(cellnum);
                cell1.setCellValue(res_res);
            }
             */
            // Cell cell = row.createCell();
            // cell.setCellValue(key);
            // Cell cell1 = row.createCell(cellnum + 2);
            //cell1.setCellValue(event.toString().substring(1, event.toString().length() - 1));
             Row row = null;
            if (i != 22) {
                row = mySheet.createRow(rownum++);
            } else {
                rownum = rownum + 3;
                row = mySheet.createRow(rownum);
            }
            i++;
            int cellnum = 0;
            Cell cell = row.createCell(cellnum++);
             cell.setCellValue(key);
             for(String val : free_space){
                 cellnum = cellnum + 2;
                 Cell cell1 = row.createCell(cellnum);
                cell1.setCellValue(val);
             }
        }
        
        /*
        for (String key_res : key_set1) {
            Row row = null;
            if (i != 22) {
                row = mySheet.createRow(rownum++);
            } else {
                rownum = rownum + 3;
                row = mySheet.createRow(rownum);
            }
            i++;

            String res_res = res.get(key_res);
            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(key_res);
            cellnum = cellnum + 2;
            Cell cell1 = row.createCell(cellnum);
            cell1.setCellValue(res_res);
        }
        */
        FileOutputStream os = new FileOutputStream(myFile);
        myWorkBook.write(os);
    }
}
