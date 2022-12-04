package com.example.study4test.controller;

import com.example.study4test.contains.ErrorCode;
import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/baiLam")
public class AssigmentController {
    @Autowired
    private AssignmentService baiLamService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseCommon PostAssigment(@RequestPart MultipartFile file, @RequestParam(name = "idU") Long idU, @RequestParam(name = "idD") Long idD) throws IOException {
        baiLamService.readFile(file,idU,idD);
        return new ResponseCommon(ErrorCode.Code.SUCCESS,ErrorCode.Type.SUCCESS,ErrorCode.Message.SUCCESS,null);
    }
    @GetMapping("/ketQua/{idb}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseCommon ShowResult(@PathVariable(name = "idb") Long idb) throws IOException {
        if(baiLamService.ResultFinal(idb).getTotal()==null)
        {
            return new ResponseCommon(ErrorCode.Code.NOT_FOUND,ErrorCode.Type.ASSIGMENT_EROS,ErrorCode.Message.NOT_EXISTS,null);
        }
        return new ResponseCommon(ErrorCode.Code.SUCCESS,ErrorCode.Type.SUCCESS,ErrorCode.Message.SUCCESS,(baiLamService.ResultFinal(idb)));

    }
    @GetMapping("/start")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void Start(){
        baiLamService.start();
    }
}

