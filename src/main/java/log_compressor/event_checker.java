/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log_compressor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import static log_compressor.import_file.import_file;
import static log_compressor.space_checker.space_checker;
import static log_compressor.write_xlsx.write_xlsx;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Zan_Wang
 */
public class event_checker {
    public static void event_checker() throws IOException, InterruptedException{
        HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
        List<String> file = import_file("D:\\log\\input.txt");
        ArrayList<Integer> server_position = new ArrayList<Integer>();
        ArrayList<String> server_list = new ArrayList<String>();
        File myFile = new File("D:\\log\\log_output.xlsx");
        if (!myFile.exists()) {
            FileOutputStream fos = new FileOutputStream(myFile);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sheet1");  

            workbook.write(fos);
            
            
        }
        int k = 0;
        for (String s : file) {
            if (s.startsWith("Server Name")) {
                int i = s.lastIndexOf(":");
                server_position.add(k);

            }
            k++;
        }
        for (int j = 0; j < server_position.size(); j++) {
            ArrayList<String> event = new ArrayList<String>();
            String servername = file.get(server_position.get(j));
            server_list.add(servername);
            if (j != server_position.size() - 1) {
                for (int l = server_position.get(j); l < server_position.get(j + 1); l++) {
                    if (file.get(l).startsWith("Event")) {
                        int eventPosition = file.get(l).lastIndexOf(":");
                        String eventId = file.get(l).substring(eventPosition + 1);
                        event.add(eventId);
                    } else if (file.get(l).startsWith("No critical")) {
                        String eventId = "Normal";
                        event.add(eventId);
                    } else if (file.get(l).startsWith("Cannot retrieve EVENT information")) {
                        String eventId = "Need maual check";
                        event.add(eventId);
                    }
                }
            } else {
                for (int l = server_position.get(server_position.size() - 1); l < file.size(); l++) {
                    if (file.get(l).startsWith("Event")) {
                        int eventPosition = file.get(l).lastIndexOf(":");
                        String eventId = file.get(l).substring(eventPosition + 1);
                        event.add(eventId);
                    } else if (file.get(l).startsWith("No critical")) {
                        String eventId = "Normal";
                        event.add(eventId);
                    } else if (file.get(l).startsWith("Cannot retrieve EVENT information")) {
                        String eventId = "Need maual check";
                        event.add(eventId);
                    }
                }
            }
            Set<String> s = new LinkedHashSet<>(event);
            List<String> event1 = new ArrayList<String>();
            event1.addAll(s);
            

            hm.put(servername, event1);
        }
        write_xlsx(hm, server_list);
        for (String server : server_list) {
            System.out.println("                               ");
            System.out.println(server);
            System.out.println(hm.get(server).toString().substring(1, hm.get(server).toString().length()-1));
            System.out.println("===============================");

        }
    }
    
}
