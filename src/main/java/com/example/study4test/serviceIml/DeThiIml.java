package com.example.study4test.serviceIml;

import com.example.study4test.entity.DeThi;
import com.example.study4test.repository.deThiRepository;
import com.example.study4test.service.DeThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DeThiIml implements DeThiService {
    @Autowired
    private deThiRepository deThiRepository;
    @Override
    public List<DeThi> getAllDe() {
        return deThiRepository.findAll();
    }


    @Override
    public void Createde(MultipartFile file, MultipartFile file2, String soP) throws IOException {
        String thangDiem = new String(file.getBytes());
        String[] lines1 = thangDiem.split("\\r\\n");
        int i = 0;
        String resul1 = "";
        while (i < lines1.length) {
            resul1 += lines1[i].concat(" ");
            i++;
        }
        String dapAn = new String(file2.getBytes());
        String[] lines2 = dapAn.split("\\r\\n");
        int j = 0;
        String resul2 = "";
        while (j < lines2.length) {
            resul2 += lines2[j].concat(" ");
            j++;
        }
        System.out.println(resul1);
        System.out.println(resul2);
        deThiRepository.save(new DeThi(resul2,soP,resul1,null,null));
    }
}
