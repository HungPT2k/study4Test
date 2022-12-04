package com.example.study4test.controller;

import com.example.study4test.contains.ErrorCode;
import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/study4")
public class PostStudy4Controller {
    @Autowired
    private ReadFileService readFileService;

    @PostMapping("/post")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseCommon getInfoFile(@RequestPart MultipartFile file, @RequestParam(name = "id") Long idDe) throws IOException {
        if (readFileService.readFile(file, idDe) == null) {

            return new ResponseCommon(ErrorCode.Code.FAIL, ErrorCode.Type.FILE_EROS, ErrorCode.Message.POST_FILE_FAIL, null);
        }
        return new ResponseCommon(ErrorCode.Code.SUCCESS, ErrorCode.Type.SUCCESS, ErrorCode.Message.SUCCESS, readFileService.readFile(file, idDe));
    }
}



