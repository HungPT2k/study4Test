package com.example.study4test.serviceIml;


import com.example.study4test.dto.ExamDTO;
import com.example.study4test.dto.FileDTO;
import com.example.study4test.entity.Question;
import com.example.study4test.entity.Part;
import com.example.study4test.repository.*;
import com.example.study4test.service.QuestionService;
import com.example.study4test.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReadFileServiceIml implements ReadFileService {
    @Value("${study4Test.source.key}")
    String keyEncode;
    @Autowired
    private CauHoiRepository cauHoiRepository;
    @Autowired
    QuestionService cauGHoiService;
    @Autowired
    private NhomCauHoiRepository nhomCauHoiRepository;
    @Autowired
    private deThiRepository deThiRepository;

    @Override
    public FileDTO readFile(MultipartFile file, Long idDe) throws IOException {
        try {
            String content = new String(file.getBytes());
            String[] lines = content.split("\\r\\n");
            LinkedHashMap<Part, List<Question>> data = new LinkedHashMap<>();
            int i = 0;
            while (i < lines.length) {
                int sttPhan = Integer.parseInt(lines[i].substring(lines[i].length() - 1));

                i++;
                while (i < lines.length && !lines[i].startsWith("Part")) {
                    String infor = null;
                    if (lines[i].startsWith("Infor")) {
                        infor = lines[i].substring(lines[i].indexOf(":") + 1);
                    }
                    Part nhomCauHoi = new Part(infor, sttPhan, null, null);
                    i++;
                    List<Question> listCauHoi = new ArrayList<>();
                    while (i < lines.length && !lines[i].startsWith("Infor") && !lines[i].startsWith("Part")) {
                        if (sttPhan == 1 || sttPhan == 2) {
                            Question cauHoi = new Question(Integer.parseInt(lines[i].substring(0, lines[i].indexOf("."))), lines[i].substring(lines[i].indexOf(".") + 1), nhomCauHoi, null, null, null, null);
                            listCauHoi.add(cauHoi);
                        } else {
                            String[] phans = lines[i].split("#");
                            String[] dapans = phans[1].split("/");
                            Question cauHoi = new Question(Integer.parseInt(phans[0].substring(0, phans[0].indexOf("."))),
                                    (sttPhan == 6) ? null : phans[0].substring(phans[0].indexOf(".") + 1)
                                    , nhomCauHoi, dapans[0].substring(dapans[0].indexOf(".")), dapans[1].substring(dapans[1].indexOf(".")), dapans[2].substring(dapans[2].indexOf(".")), dapans[3].substring(dapans[3].indexOf(".")));
                            listCauHoi.add(cauHoi);
                        }
                        i++;
                    }
                    data.put(nhomCauHoi, listCauHoi);
                }

            }
            Set<Part> set = data.keySet();
            for (Part key : set) {
                String noiDung = Base64.getEncoder().encodeToString(key.getNoiDung().getBytes());
                //encrypt.Encode(key.getNoiDung(),keyEncode);//Base64.getEncoder().encodeToString((key.getNoiDung().getBytes()));
                Part nhomCauHoi = new Part(noiDung, key.getSttPhan(), null, deThiRepository.getById(idDe));
                nhomCauHoiRepository.save(nhomCauHoi);
                for (Question cau : data.get(key)) {
                    String text = cau.getTexts();
                    if (text != null) text = Base64.getEncoder().encodeToString(cau.getTexts().getBytes());
                    //   encrypt.Encode(cau.getTexts(),keyEncode);
                    String da1 = cau.getDaAn1();
                    if (da1 != null) da1 = Base64.getEncoder().encodeToString(cau.getDaAn1().getBytes());
                    //  encrypt.Encode(cau.getDaAn1(),keyEncode);
                    String da2 = cau.getDaAn2();
                    if (da2 != null) da2 = Base64.getEncoder().encodeToString(cau.getDaAn2().getBytes());
                    //encrypt.Encode(cau.getDaAn2(),keyEncode);
                    String da3 = cau.getDaAn3();
                    if (da3 != null) da3 = Base64.getEncoder().encodeToString(cau.getDaAn3().getBytes());
                    //   encrypt.Encode(cau.getDaAn3(),keyEncode);
                    String da4 = cau.getDaAn4();
                    if (da4 != null) da4 = Base64.getEncoder().encodeToString(cau.getDaAn4().getBytes());
                    //  encrypt.Encode(cau.getDaAn4(),keyEncode);
                    cauHoiRepository.save(new Question(cau.getStt(), text, nhomCauHoi, da1, da2, da3, da4));
                }
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            return new FileDTO(dtf.format(now), idDe);
        } catch (Exception e) {
            return null;
        }
    }
}

