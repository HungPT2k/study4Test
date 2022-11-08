package com.example.study4test.serviceIml;

import com.example.study4test.entity.CauHoi;
import com.example.study4test.entity.DapAn;
import com.example.study4test.entity.DeThi;
import com.example.study4test.entity.NhomCauHoi;
import com.example.study4test.repository.*;
import com.example.study4test.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class ReadFileServiceIml implements ReadFileService {
    @Autowired
    private CauHoiRepository cauHoiRepository;
    @Autowired
    private NhomCauHoiRepository nhomCauHoiRepository;
    @Autowired
    private DapAnRepository dapAnRepository;
    @Override
    public void readFile(MultipartFile file,Long idDe) throws IOException {
        String content = new String(file.getBytes());
        String[] lines = content.split("\\r\\n");
        int i = 0;
        while (i < lines.length) {
         int sttPhan=  Integer.parseInt(lines[i].substring(lines[i].length() - 1));
i++;
            while (i < lines.length && !lines[i].startsWith("Part")) {
                NhomCauHoi nhomCauHoi = new NhomCauHoi(null, sttPhan, null,null);
               nhomCauHoiRepository.save(nhomCauHoi);
                i++;
                while (i < lines.length && !lines[i].startsWith("Infor") && !lines[i].startsWith("Part")) {
                    if (sttPhan == 1 || sttPhan == 2) {
                        CauHoi cauHoi = new CauHoi(Integer.parseInt(lines[i].substring(0, lines[i].indexOf("."))), lines[i].substring(lines[i].indexOf(".")+1),null,
                                null);
                        cauHoiRepository.save(cauHoi);
                        for (int j = 1; j <= 4; j++) {
                            DapAn dapAn = new DapAn(j, null, cauHoi);
                            dapAnRepository.save(dapAn);
                        }
                    } else {
                        String[] phans = lines[i].split("#");
                        CauHoi cauHoi = new CauHoi(Integer.parseInt(phans[0].substring(0, phans[0].indexOf("."))),
                                (sttPhan == 6) ? null : phans[0].substring(phans[0].indexOf(".") + 1)
                           , null, null);
                        cauHoiRepository.save(cauHoi);
                        String[] dapans = phans[1].split("/");
//                        if (phans[0].startsWith("165")) {
//                            System.out.println();
//                        }
                        for (int j = 0; j < dapans.length; j++) {
                            DapAn dapAn = new DapAn(j + 1, dapans[j].substring(dapans[j].indexOf(".")), cauHoi);
                            dapAnRepository.save(dapAn);
                        }
                    }
                    i++;
                }
            }
        }
    }
    }

