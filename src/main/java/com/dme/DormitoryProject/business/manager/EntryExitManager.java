package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.business.services.IEntryExitService;
import com.dme.DormitoryProject.dtos.entryExit.EntryExitDTO;
import com.dme.DormitoryProject.dtos.entryExit.EntryExitMapper;
import com.dme.DormitoryProject.dtos.punishment.PunishmentDTO;
import com.dme.DormitoryProject.dtos.punishment.PunishmentMapper;
import com.dme.DormitoryProject.dtos.studentDtos.StudentMapper;
import com.dme.DormitoryProject.entity.EntryExit;
import com.dme.DormitoryProject.entity.Punishments;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.enums.EntryExit.EntryOrExit;
import com.dme.DormitoryProject.enums.punishment.PunishmentType;
import com.dme.DormitoryProject.repository.IEntryExitDao;
import com.dme.DormitoryProject.repository.IPunishmentDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccessDataResult;
import com.dme.DormitoryProject.statusCode.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntryExitManager extends BaseClass implements IEntryExitService {

    private IEntryExitDao entryExitDao;
    private IStudentDao studentDao;
    private IPunishmentDao punishmentDao;

    @Autowired
    public EntryExitManager(IEntryExitDao entryExitDao, IStudentDao studentDao, IPunishmentDao punishmentDao){
        this.entryExitDao=entryExitDao;
        this.studentDao=studentDao;
        this.punishmentDao=punishmentDao;
    }

    @Override
    public Result getAll(){
        try {
            List<EntryExitDTO> entryExits = entityToDtoList(entryExitDao.findAll(), EntryExitMapper::toDto);
            return new SuccessDataResult(JsonFileReader.getMessage("200","en"),true,entryExits);
        } catch (Exception e) {
            return new ErrorResult(JsonFileReader.getMessage("501","tr"),false);
        }
    }

    @Override
    public Result entry(Long id) {
        EntryExit entryExit = new EntryExit();
        entryExit.setStudent(studentDao.getById(id));
        Student student = studentDao.getById(id);
        String message = JsonFileReader.getMessage("563","tr");

        if (!(entryExitDao.existByStudentId(id).isEmpty()) && inOrOutControl(id).equals(JsonFileReader.getMessage("561","tr"))){
            return new ErrorResult(JsonFileReader.getMessage("561","tr"),false);
        }
        if (checkEntryTimeControl()){
            addPenaltyStudent(student,LocalTime.now(),7L);
            int newScore = student.getScore() - 10;
            student.setScore(newScore);
            studentDao.save(student);
            message = JsonFileReader.getMessage("564","tr");
        }

        entryExit.setEntryExit(EntryOrExit.Entry);
        entryExitDao.save(entryExit);
        return new SuccessDataResult(message,true,entityToDto(studentDao.getById(id), StudentMapper::toDto));
    }

    @Override
    public Result exit(Long id) {
        if (inOrOutControl(id).equals(JsonFileReader.getMessage("562","tr"))){
            return new ErrorResult(JsonFileReader.getMessage("562","tr"),false);
        }
        EntryExit entryExit = new EntryExit();
        entryExit.setStudent(studentDao.getById(id));
        entryExit.setEntryExit(EntryOrExit.Exit);
        entryExitDao.save(entryExit);
        return new SuccessDataResult(JsonFileReader.getMessage("565","tr"),true,entityToDto(studentDao.getById(id), StudentMapper::toDto));
    }

    private String inOrOutControl(Long id){
        Optional<EntryExit> entryExit = entryExitDao.findLatestEntryExitByStudentId(id);
        if (entryExit.get().getEntryExit() == EntryOrExit.Entry){
            return JsonFileReader.getMessage("561","tr");
        }
        if (entryExit.get().getEntryExit() == EntryOrExit.Exit){
            return JsonFileReader.getMessage("562","tr");
        }
        return JsonFileReader.getMessage("501","tr");
    }

    private boolean checkEntryTimeControl(){
        LocalTime currentTime = LocalTime.of(23,30,00);
        //LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.of(23, 0); // 23:00:00
        LocalTime endTime = LocalTime.of(6, 0);   // 06:00:00

        boolean isNotInRange = currentTime.isAfter(startTime) || currentTime.isBefore(endTime);
        return isNotInRange;
    }

    @Scheduled(cron = "00 00 06 * * ?")
    private void whichStudentOutside(){
        List<Student> studentList = studentDao.findAll();
        List<Student> didntComeStudents = new ArrayList<>();

        for (Student student : studentList){
            Optional<EntryExit> entryExit = entryExitDao.findLatestEntryExitByStudentId(student.getId());
            if (entryExit.get().getEntryExit() == EntryOrExit.Exit){
                didntComeStudents.add(student);
                student.setScore(student.getScore() - 20);
                studentDao.save(student);
            }
        }
        for (Student student : didntComeStudents){
            addPenaltyStudent(student,LocalTime.of(6,0,0),8L);
        }
    }

    private void addPenaltyStudent(Student student, LocalTime time,Long id){
        PunishmentDTO punishmentDto = new PunishmentDTO();
        punishmentDto.setPunishmentDefinitionsId(id);
        punishmentDto.setStudentId(student.getId());
        punishmentDto.setPunishmentTime(time);
        punishmentDao.save(dtoToEntity(punishmentDto, PunishmentMapper::toEntity));
    }
}
