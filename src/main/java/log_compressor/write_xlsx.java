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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static log_compressor.event_checker.event_checker;
import static log_compressor.space_checker.space_checker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Zan_Wang
 */
public class write_xlsx {

    public static void write_xlsx(HashMap<String, List<String>> map, ArrayList<String> server_list) throws FileNotFoundException, IOException {
        File myFile = new File("D:\\log\\log_output.xlsx");

        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        BusinessOrInfra boi = new BusinessOrInfra();
        boolean isInfra = boi.isInfrastructure();
        int rownum = mySheet.getLastRowNum() + 1;
        Row row1 = mySheet.createRow(rownum++);
        Cell cell2 = row1.createCell(0);
        Date date = new Date();
        cell2.setCellValue(date.toString());
        int i = 0;
        for (String key : server_list) {
            Row row = null;
            if (isInfra) {
                if (i != 22) {
                    row = mySheet.createRow(rownum++);
                    i++;
                } else {
                    rownum = rownum + 2;
                    row = mySheet.createRow(rownum++);
                    i++;
                }
                
            } else {
                row = mySheet.createRow(rownum++);
                i++;
            }

            int cellnum = 0;

            List<String> event = map.get(key);

            Cell cell = row.createCell(cellnum);
            cell.setCellValue(key);
            Cell cell1 = row.createCell(cellnum + 2);
            cell1.setCellValue(event.toString().substring(1, event.toString().length() - 1));
        }
        FileOutputStream os = new FileOutputStream(myFile);
        myWorkBook.write(os);
    }

}
