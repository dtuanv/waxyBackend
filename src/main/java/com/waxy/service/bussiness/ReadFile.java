package com.waxy.service.bussiness;


import com.ricesoft.ExcelReader;
import com.ricesoft.HeaderData;
import org.example.Tuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadFile {
    String excelFilePath = "T:\\Project\\Backend\\File\\nhanh.xlsx";




    public void hi(Tuan tuan){
        tuan.run();
        System.out.println(excelFilePath);
        HeaderData headerData = new HeaderData();
        String excelFilePath = "T:\\Project\\Backend\\File\\nhanh.xlsx";
        headerData.extracted(excelFilePath);
    }


}
