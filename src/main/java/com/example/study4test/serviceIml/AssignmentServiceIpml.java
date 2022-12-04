package com.example.study4test.serviceIml;

import com.example.study4test.contains.ResponseResultFinal;
import com.example.study4test.entity.Assignment;
import com.example.study4test.entity.ResultDetail;
import com.example.study4test.repository.BaiLamRepository;
import com.example.study4test.repository.UserRepository;
import com.example.study4test.repository.deThiRepository;
import com.example.study4test.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Service
public class AssignmentServiceIpml implements AssignmentService {
    @Value("${study4Test.source.thangDiem.2022}")
    String thangDiem;
    @Autowired
    private BaiLamRepository baiLamRepository;
    @Autowired
    private deThiRepository deThiRepository;
    @Autowired
    @Lazy // tránh phụ thuộc vòng;
    private AssignmentService baiLamService;
    @Autowired
    private UserRepository userRepository;

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
    public void readFile(MultipartFile file, Long idU, Long idDe) throws IOException {
        String content = new String(file.getBytes());
        String[] lines = content.split("\\r\\n");
        int i = 0;
        String resul = "";
        while (i < lines.length) {
            resul += lines[i].concat(" ");
            i++;
        }
        String start = baiLamService.start();
        String end;
        // create an instance of Instant class for getting current UTC time
        Instant instant = Instant.now();
        // use toString() method to convert instant object into String
        end = instant.toString();
        resul = Base64.getEncoder().encodeToString(resul.getBytes());
        //encrypt.Encode(resul,keyEncode);
        baiLamRepository.save(new Assignment("start " + start + " end " + end, resul, deThiRepository.getById(idDe), userRepository.getById(idU)));
        //  System.out.println(resul);
    }

    @Override
    public ResponseResultFinal ResultFinal(Long idB) throws IOException {
        //   String keyDecode=keyEncode;
        //  byte[] decodedBytes = Base64.getDecoder().decode(baiLamRepository.getById(idB).getCauTraLoi());

        Optional<Assignment> checkID = baiLamRepository.findById(idB);
        if (checkID.isEmpty()) return new ResponseResultFinal(null, null, null, null);

        // Giải mã
        byte[] decodedBytes = Base64.getDecoder().decode(baiLamRepository.getById(idB).getCauTraLoi());
        String baiLam = new String(decodedBytes);
        //   encrypt.Decode(baiLamRepository.getById(idB).getCauTraLoi(),keyDecode);
        byte[] decodedBytes1 = Base64.getDecoder().decode(baiLamRepository.getById(idB).getDeThi().getDapAnDung());
        String daAn = new String(decodedBytes1);
        // encrypt.Decode(baiLamRepository.getById(idB).getDeThi().getDapAnDung(),keyDecode);
        byte[] decodedBytes2 = Base64.getDecoder().decode(baiLamRepository.getById(idB).getDeThi().getThangDiem());
        String thangDiem = new String(decodedBytes2);
        //encrypt.Decode(baiLamRepository.getById(idB).getDeThi().getThangDiem(),keyDecode);

        // Lưu thang điểm toiec
        LinkedHashMap<String, String> ThangDiem = new LinkedHashMap<>();
        // lưu đáp án đúng
        LinkedHashMap<String, String> daAnDung = new LinkedHashMap<>();
        // lưu đáp án User
        LinkedHashMap<String, String> daAnLam = new LinkedHashMap<>();
        // lưu bài làm chi tiết
        LinkedHashMap<String, String> chiTietBailam = new LinkedHashMap<>();

        // tách chuỗi
        String[] content1 = baiLam.split("\\s+");
        String[] content2 = daAn.split("\\s+");
        String[] content3 = thangDiem.split("-");

        // đếm số câu đúng;
        int nghe = 0, doc = 0;
        for (int i = 0; i < content1.length - 1; i++) {
            String[] x = content1[i].split("\\.");
            String[] y = content2[i].split("\\.");
            if (Integer.parseInt(x[0]) <= 100) {
                if (x[1].equals(y[1])) {
                    nghe++;
                    chiTietBailam.put(x[0], x[1]);
                } else chiTietBailam.put(x[0], x[1] + "-->" + y[1]);
            } else {
                if (x[1].equals(y[1])) {
                    doc++;
                    chiTietBailam.put(x[0], x[1]);
                } else chiTietBailam.put(x[0], x[1] + "-->" + y[1]);
            }
        }


        Set<String> stt = chiTietBailam.keySet();
        List<Object> data = new ArrayList<>();
        for (String x : stt) {
            ResultDetail chiTiet = new ResultDetail(Integer.parseInt(x), chiTietBailam.get(x));
            data.add(chiTiet);
        }
        // chấm điểm
        boolean check1 = false;
        boolean check2 = false;
        String message1 = null, message2 = null;
        int d1 = 0, d2 = 0;
        for (int i = 0; i < content3.length; i++) {
            String[] x = content3[i].split("\\.");
            if (nghe == i + 1) {
                d1 = Integer.parseInt(x[0]);
                message1 = "Đúng " + nghe + " câu nghe đạt " + x[0] + " điểm nghe";
                check1 = true;
            }
            if (doc == i + 1) {
                d2 = Integer.parseInt(x[1]);
                message2 = "Đúng " + doc + " câu đọc đạt " + x[1] + " điểm đọc";
                check2 = true;
            }
            if (check1 && check2) break;
        }
        int tong = d1 + d2;
        // KetQua ketQuaDTO = new KetQua(data,message1,message2);
        return new ResponseResultFinal("Thi sinh  " + baiLamRepository.getById(idB).getUsers().getUsername() + " đạt " + tong + " điểm", message1, message2, data);
    }


}
