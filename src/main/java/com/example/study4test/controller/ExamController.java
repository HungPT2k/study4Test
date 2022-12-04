package com.example.study4test.controller;

import com.example.study4test.contains.ErrorCode;
import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.dto.QuestionDTO;
import com.example.study4test.entity.Question;
import com.example.study4test.service.QuestionService;
import com.example.study4test.service.ExamService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/de")
public class ExamController {
    @Autowired
    private ExamService deThiService;
    @Autowired
    QuestionService cauHoiService;

    @GetMapping("/all/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    ResponseCommon S(@PathVariable Long id) {
        if (deThiService.ExamDetail(id) == null) {
            return new ResponseCommon(ErrorCode.Code.NOT_FOUND, ErrorCode.Type.EXAM_EROS, ErrorCode.Message.NOT_EXISTS, null);
        }
        return new ResponseCommon(ErrorCode.Code.SUCCESS, ErrorCode.Type.SUCCESS, ErrorCode.Message.SUCCESS, deThiService.ExamDetail(id)); // convert list entity to list DTO
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseCommon CreateDethi(@RequestPart MultipartFile file2, @RequestParam(name = "soP") String soP, Long id) throws IOException {
        if (deThiService.CreateExam(file2, soP, id).getUserCreate() == null) {
            return new ResponseCommon(ErrorCode.Code.NOT_FOUND, ErrorCode.Type.FILE_EROS, ErrorCode.Message.NOT_EXISTS, null);
        }
        return new ResponseCommon(ErrorCode.Code.SUCCESS, ErrorCode.Type.SUCCESS, ErrorCode.Message.SUCCESS, deThiService.CreateExam(file2, soP, id)); // convert list entity to list DTO

    }

    @PutMapping("/update/{idD}/{phan}/{stt}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseCommon UpdateExam(@RequestBody Question cauHoi, @PathVariable Long idD, @PathVariable int phan, @PathVariable int stt) {
        if (cauHoiService.Update(cauHoi, idD, phan, stt) == null) {
            return new ResponseCommon(ErrorCode.Code.NOT_FOUND, ErrorCode.Type.UPDATE_FAIL, ErrorCode.Message.UPDATE_FAIL, null);
        }
        return new ResponseCommon(ErrorCode.Code.SUCCESS, ErrorCode.Type.SUCCESS, ErrorCode.Message.SUCCESS, cauHoiService.Update(cauHoi, idD, phan, stt));
    }
}
