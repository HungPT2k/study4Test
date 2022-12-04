package com.example.study4test.serviceIml;

import com.example.study4test.dto.QuestionDTO;
import com.example.study4test.entity.Question;
import com.example.study4test.entity.Part;
import com.example.study4test.repository.CauHoiRepository;
import com.example.study4test.repository.deThiRepository;
import com.example.study4test.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
public class QuestionServiceIpml implements QuestionService {
    @Value("${study4Test.source.key}")
    String keyEncode;
    @Autowired
    private CauHoiRepository cauHoiRepository;
    @Autowired
    private deThiRepository deThiRepository;

    @Override
    @Transactional
    public void saveCauHoi(Question cauHoi) {
        cauHoiRepository.save(cauHoi);
    }

    @Override
    @Transactional
    public QuestionDTO Update(Question cauHoi, Long idD, int phan, int stt) {
        try {
            List<Part> listNhom = deThiRepository.findById(idD).get().getNhomCauHois();
            Question cauHoi1 = listNhom.stream().filter(n -> n.getSttPhan() == phan).toList().get(0).getCauHois().stream().filter(c -> c.getStt() == stt).toList().get(0);
            cauHoi1.setTexts(Base64.getEncoder().encodeToString(cauHoi.getTexts().getBytes()));
            cauHoi1.setDaAn1(Base64.getEncoder().encodeToString(cauHoi.getDaAn1().getBytes()));
            cauHoi1.setDaAn2(Base64.getEncoder().encodeToString(cauHoi.getDaAn2().getBytes()));
            cauHoi1.setDaAn3(Base64.getEncoder().encodeToString(cauHoi.getDaAn3().getBytes()));
            cauHoi1.setDaAn4(Base64.getEncoder().encodeToString(cauHoi.getDaAn4().getBytes()));
            ModelMapper mapper = new ModelMapper();
            return mapper.map(cauHoiRepository.save(cauHoi1), QuestionDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

}
