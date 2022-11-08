package com.example.study4test.serviceIml;

import com.example.study4test.entity.BaiLam;
import com.example.study4test.repository.BaiLamRepository;
import com.example.study4test.repository.deThiRepository;
import com.example.study4test.service.BaiLamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

@Service
public class BaiLamIml implements BaiLamService {
    @Autowired
    private BaiLamRepository baiLamRepository;
    @Autowired
    private deThiRepository  deThiRepository;

    @Override
    public void readFile(MultipartFile file,Long idU, Long idDe) throws IOException {
        String content = new String(file.getBytes());
        String[] lines = content.split("\\r\\n");
        int i = 0;
        String resul = "";
        while (i < lines.length) {
            resul += lines[i].concat("|");
            i++;
        }
        baiLamRepository.save(new BaiLam(null,resul,deThiRepository.getById(idDe)));
        System.out.println(resul);
//        LinkedHashMap<String,String> listDa= new LinkedHashMap<>();
//        int i = 0;
//        while (i < lines.length) {
//            String[] x=lines[i].split("\\.");
//           listDa.put(x[0],x[1]);
//           i++;
//        }
//        Set<String> keySet = listDa.keySet();
//        for (String key : keySet) {
//            System.out.println(key + " - " + listDa.get(key));
//        }
//        System.out.println(listDa);
//    }
    }

    @Override
    public void KetQua(Long idB) throws IOException {
      String baiLam=  baiLamRepository.getById(idB).getCauTraLoi();
      String daAn = baiLamRepository.getById(idB).getDeThi().getDapAnDung();
      String thangDiem = baiLamRepository.getById(idB).getDeThi().getDapAnDung();
        LinkedHashMap<String,String> ThangDoc= new LinkedHashMap<>();
        LinkedHashMap<String,String> ThangNghe= new LinkedHashMap<>();
        LinkedHashMap<String,String> daNghe= new LinkedHashMap<>();
        LinkedHashMap<String,String> daDoc= new LinkedHashMap<>();
        LinkedHashMap<String,String> listDoc= new LinkedHashMap<>();
        LinkedHashMap<String,String> listNghe= new LinkedHashMap<>();
        String[] content1=baiLam.split("|");
        String[] content2=daAn.split("|");
        String[] content3=thangDiem.split("|");
        for( String a:content1){
            String[] x= a.split(".");
            if(Integer.parseInt(x[0])<=100)
            {
                listNghe.put(x[0],x[1]);
            }
            else listDoc.put(x[0],x[1]);
        }
        for( String a:content2){
            String[] x= a.split(".");
            if(Integer.parseInt(x[0])<=100)
            {
                daNghe.put(x[0],x[1]);
            }
            else daDoc.put(x[0],x[1]);
        }
        for( String a:content3){
            String[] x= a.split(".");
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
                System.out.println("Đúng"+nghe+"câu nghe dat" + ThangNghe.get(x)+ "diem nghe");
                break;
            }
        }
        for(String x:set3){
            if(doc==Integer.parseInt(x)) {
                System.out.println("Đúng"+doc+"câu doc dat" + ThangDoc.get(x)+ "diem doc");
                break;
            }
        }
    }
}
