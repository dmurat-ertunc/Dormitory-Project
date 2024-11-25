package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IStudentGetPermissionService;
import com.dme.DormitoryProject.dtos.studentGetPermission.StudentGetPermissionDTO;
import com.dme.DormitoryProject.dtos.studentGetPermission.StudentGetPermissionMapper;
import com.dme.DormitoryProject.entity.Mail;
import com.dme.DormitoryProject.entity.StudentGetPermission;
import com.dme.DormitoryProject.repository.IStudentGetPermissionDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGetPermissionManager extends BaseClass implements IStudentGetPermissionService {

    private IStudentGetPermissionDao studentGetPermissionDao;

    @Autowired
    public StudentGetPermissionManager(IStudentGetPermissionDao studentGetPermissionDao){
        this.studentGetPermissionDao=studentGetPermissionDao;
    }

    @Override
    public Result getStudentPermission() {
        try {
            List<StudentGetPermissionDTO> studentGetPermissionDTOS = entityToDtoList(studentGetPermissionDao.findAll(), StudentGetPermissionMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","tr"),true,studentGetPermissionDTOS);
        }catch (Exception e){
            return new ErrorResult(JsonFileReader.getMessage("404","tr"),false);
        }
    }

    @Override
    public Result acceptStudentPermission(Long id) {
        try {
            StudentGetPermission studentGetPermission = studentGetPermissionDao.getById(id);
            studentGetPermission.setApproval(true);
            sendMailStudent(studentGetPermission.getStudent().getMail());
            studentGetPermissionDao.save(studentGetPermission);
            return new SuccesResult(JsonFileReader.getMessage("202","tr"),true);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("404","tr"),false);
        }
    }

    private void sendMailStudent(String mail){
        Mail sendMail = new Mail();
        sendMail.setFromMail("cengdme@gmail.com");
        sendMail.setToMail(String.valueOf(mail));
        sendMail.setSubject("İzin Talebi Cevabı");
        sendMail.setText("İzin talebiniz onaylanmışıtır");
        sendMail(sendMail);
    }
}
