package com.example.study4test.serviceIml;

import antlr.Token;
import com.example.study4test.contains.ErrorCode;
import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.contains.ResponseResultFinal;
import com.example.study4test.dto.ExamDTO;
import com.example.study4test.dto.QuestionDTO;
import com.example.study4test.entity.Assignment;
import com.example.study4test.entity.Question;
import com.example.study4test.entity.Exam;
import com.example.study4test.repository.CauHoiRepository;
import com.example.study4test.repository.UserRepository;
import com.example.study4test.repository.deThiRepository;
import com.example.study4test.service.ExamService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceIpml implements ExamService {
    @Value("${study4Test.source.thangDiem.2022}")
    String resul1;
    @Value("${study4Test.source.key}")
    String keyEncode;
    @Autowired
    private deThiRepository deThiRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CauHoiRepository cauHoiRepository;

    @Override
    public List<QuestionDTO> ExamDetail(Long id) {
        Optional<Exam> checkID = deThiRepository.findById(id);
        if (checkID.isEmpty()) return null;
        String keyDecode = keyEncode;
        ModelMapper mapper = new ModelMapper();
        List<Question> listCau = cauHoiRepository.getAllByIdd(id).stream().peek(cauHoi -> {

            if (cauHoi.getTexts() != null)  //cauHoi.setTexts(encrypt.Decode(cauHoi.getTexts(),keyDecode));
            {
                byte[] decodedBytes2 = Base64.getDecoder().decode(cauHoi.getTexts());
                cauHoi.setTexts(new String(decodedBytes2));
            }
            if (cauHoi.getDaAn1() != null)  //cauHoi.setDaAn1(encrypt.Decode(cauHoi.getDaAn1(),keyDecode));
            {
                byte[] decodedBytes2 = Base64.getDecoder().decode(cauHoi.getDaAn1());
                cauHoi.setDaAn1(new String(decodedBytes2));
            }
            if (cauHoi.getDaAn2() != null)  //cauHoi.setDaAn2(encrypt.Decode(cauHoi.getDaAn2(),keyDecode));
            {
                byte[] decodedBytes2 = Base64.getDecoder().decode(cauHoi.getDaAn2());
                cauHoi.setDaAn2(new String(decodedBytes2));
            }
            if (cauHoi.getDaAn3() != null) //cauHoi.setDaAn3(encrypt.Decode(cauHoi.getDaAn3(),keyDecode));
            {
                byte[] decodedBytes2 = Base64.getDecoder().decode(cauHoi.getDaAn3());
                cauHoi.setDaAn3(new String(decodedBytes2));
            }
            if (cauHoi.getDaAn4() != null)  //cauHoi.setDaAn4(encrypt.Decode(cauHoi.getDaAn4(),keyDecode));
            {
                byte[] decodedBytes2 = Base64.getDecoder().decode(cauHoi.getDaAn4());
                cauHoi.setDaAn4(new String(decodedBytes2));
            }
        }).toList();
        return mapper.map(listCau, new TypeToken<List<QuestionDTO>>() {
        }.getType());// convert list entity to list DTO
    }


    @Override
    public ExamDTO CreateExam(MultipartFile file2, String soP, Long idU) throws IOException {
        try {
            String dapAn = new String(file2.getBytes());
            String[] lines2 = dapAn.split("\\r\\n");
            int j = 0;
            String resul2 = "";
            while (j < lines2.length) {
                resul2 += lines2[j].concat(" ");
                j++;
            }
            resul1 = Base64.getEncoder().encodeToString(resul1.getBytes());
            //    encrypt.Encode(resul1,keyEncode);
            resul2 = Base64.getEncoder().encodeToString(resul2.getBytes());
            //     encrypt.Encode(resul2,keyEncode);
            System.out.println(resul1);
            System.out.println(resul2);
            deThiRepository.save(new Exam(resul2, soP, resul1, null, null, userRepository.getById(idU)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            return new ExamDTO(dtf.format(now), userRepository.getById(idU).getUsername(), soP);
        } catch (Exception e) {
            return new ExamDTO(null, null, null);
        }
    }
}
