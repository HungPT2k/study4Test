package com.example.study4test.serviceIml;

import com.example.study4test.entity.BaiLam;
import com.example.study4test.repository.BaiLamRepository;
import com.example.study4test.repository.deThiRepository;
import com.example.study4test.service.BaiLamService;
import com.example.study4test.service.DeThiService;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TimeZone;

@Service
public class BaiLamIml implements BaiLamService {
    @Autowired
    private BaiLamRepository baiLamRepository;
    @Autowired
    private deThiRepository  deThiRepository;
    @Autowired
    @Lazy // tránh phụ thuộc vòng;
    private BaiLamService baiLamService;
    @Override
    public String start() {
        String d1;
        // create an instance of Instant class for getting current UTC time
        Instant instant = Instant.now();
        // use toString() method to convert instant object into String
        d1 = instant.toString();
        // pass UTC date to main method.
        return d1;

}

    @Override
    public void readFile(MultipartFile file,Long idU, Long idDe) throws IOException {
        String content = new String(file.getBytes());
        String[] lines = content.split("\\r\\n");
        int i = 0;
        String resul = "";
        while (i < lines.length) {
            resul += lines[i].concat(" ");
            i++;
        }
       String start= baiLamService.start();
        String end;
        // create an instance of Instant class for getting current UTC time
        Instant instant = Instant.now();
        // use toString() method to convert instant object into String
        end = instant.toString();
        baiLamRepository.save(new BaiLam("start "+start+" end "+end,resul,deThiRepository.getById(idDe)));
        System.out.println(resul);
    }

    @Override
    public void KetQua(Long idB) throws IOException {
      String baiLam=  baiLamRepository.getById(idB).getCauTraLoi();
      String daAn = baiLamRepository.getById(idB).getDeThi().getDapAnDung();
      String thangDiem = baiLamRepository.getById(idB).getDeThi().getThangDiem();
        LinkedHashMap<String,String> ThangDoc= new LinkedHashMap<>();
        LinkedHashMap<String,String> ThangNghe= new LinkedHashMap<>();
        LinkedHashMap<String,String> daNghe= new LinkedHashMap<>();
        LinkedHashMap<String,String> daDoc= new LinkedHashMap<>();
        LinkedHashMap<String,String> listDoc= new LinkedHashMap<>();
        LinkedHashMap<String,String> listNghe= new LinkedHashMap<>();
        String[] content1=baiLam.split("\\s+");
        String[] content2=daAn.split("\\s+");
        String[] content3=thangDiem.split("\\s+");
        for(int i=0 ; i<content1.length-1;i++){
            String[] x= content1[i].split("\\.");
            if(Integer.parseInt(x[0])<=100)
            {
                listNghe.put(x[0],x[1]);
            }
            else listDoc.put(x[0],x[1]);
        }
        for(int i=0 ; i<content2.length-1;i++){
            String[] x= content2[i].split("\\.");
            if(Integer.parseInt(x[0])<=100)
            {
                daNghe.put(x[0],x[1]);
            }
            else daDoc.put(x[0],x[1]);
        }
        for(int i=0 ; i<content3.length-1;i++){
            String[] x= content3[i].split("\\.");
            ThangNghe.put(x[0],x[1]);
            ThangDoc.put(x[0],x[2]);
        }
        Set<String> set = listNghe.keySet();
        Set<String> set2 = listDoc.keySet();
        int nghe=0,doc=0;
        for( String x:set){
            if(daNghe.get(x).equals(listNghe.get(x))) nghe++;
        }
        for(String x:set2){
            if(daDoc.get(x).equals(listDoc.get(x))) doc++;
        }
        Set<String> set3=ThangDoc.keySet();
        for(String x:set3){
            if(nghe==Integer.parseInt(x)) {
                System.out.println("Đúng "+nghe+" câu nghe dat " + ThangNghe.get(x)+ " diem nghe");
                break;
            }
        }
        for(String x:set3){
            if(doc==Integer.parseInt(x)) {
                System.out.println("Đúng "+doc+" câu doc dat " + ThangDoc.get(x)+ " diem doc");
                break;
            }
        }
    }


}
