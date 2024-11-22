package com.dme.DormitoryProject.business.manager;

import com.dme.DormitoryProject.business.services.IRedisService;
import com.dme.DormitoryProject.business.services.IRoomService;
import com.dme.DormitoryProject.business.services.IStudentService;
import com.dme.DormitoryProject.business.services.IUserService;
import com.dme.DormitoryProject.base.BaseClass;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.mernis.DBQKPSPublicSoap;
import com.dme.DormitoryProject.repository.*;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentManager extends BaseClass implements IStudentService{

    private IStudentDao studentDao;
    private IRentalDao rentalDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IUniversityDao universityDao;
    private IRedisService redisService;
    private IUserService userService;
    private IRoomDao roomDao;

    @Autowired
    public StudentManager(IStudentDao studentDao, IRentalDao rentalDao,
                          ILgoDao lgoDao, ILogLevelDao logLevelDao, IUniversityDao universityDao,
                          IRedisService redisService, IUserService userService, IRoomDao roomDao) {
        this.studentDao = studentDao;
        this.rentalDao = rentalDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.universityDao = universityDao;
        this.redisService=redisService;
        this.userService=userService;
        this.roomDao=roomDao;
    }

    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setMessage(message);
        lgoDao.save(log);
    }

    @Override
    public Result getAll(){
        try {
            List<StudentDTO> studentDTOS = entityToDtoList(studentDao.findAll(),StudentMapper::toDto);
            LogLevelSave(2,"Tüm öğrenciler listelendi");
            return new SuccessDataResult("Tüm öğrenciler listelendi",true,studentDTOS);
        }catch (Exception e){
            LogLevelSave(1,"Tüm öğrencilerin listelenmesinde bir hata oluştu");
            return new ErrorResult("Tüm öğrenciler listelenirken hata oluştu",false);
        }
    }

    @Override
    public Result findStudentById(Long id){
        try{
            StudentDTO studentDTO = entityToDto(studentDao.getById(id),StudentMapper::toDto);
            LogLevelSave(2,"İd değerine göre öğrenci listelendi");

            return new SuccessDataResult("İd değerine göre öğrenci listelendi",true,studentDTO);
        }catch (Exception e){
            LogLevelSave(1,"İd değerine göre öğrenci bulunamadı");
            return new ErrorResult("İd değerine göre öğrenci bulunamadı",false);
        }
    }

    @Override
    public List<Student> findStudentsOlderThan18Native(){
        return studentDao.findStudentsOlderThan18Native();
    }

    @Override
    public Result saveStudent(StudentDTO studentDTO, String password){
        DBQKPSPublicSoap client = new DBQKPSPublicSoap();
        Integer year = studentDTO.getBirthDate().getYear();
        Long tcNo = Long.parseLong(studentDTO.getTcNo());
        try {
            boolean isRealPerson = client.TCKimlikNoDogrula(tcNo,studentDTO.getName(),studentDTO.getSurName(),year);
            if(isRealPerson){
                List<Student> students = studentDao.findAll();
                if (uniqueControl(students,studentDTO,"getMail") || uniqueControl(students,studentDTO,"getTcNo")){
                    LogLevelSave(1,"Mail veya kimlik nummarası benzersiz olmalıdır");
                    return new ErrorResult("Mail veya kimlik numarası benzersiz olmalıdır",false);
                }
                try {
                    studentDTO.setVerify(false);
                    userService.saveDormitoryUser(studentDTO,"ROLE_STUDENT",password,studentDTO.getName(),studentDTO.getSurName());
                    studentDao.save(dtoToEntity(studentDTO,StudentMapper::toEntity));
                    LogLevelSave(3,"Öğrenci ekleme işlemi başarılı");
                    return new SuccessDataResult("Öğrenci ekleme işlemi başarılı",true,studentDTO);
                }catch (Exception e){
                    LogLevelSave(1,"Öğrenci ekleme işleminde hata oluştu");
                    return new ErrorResult("Öğrenci ekleme  işleminde hata oluştu",false);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ErrorResult("Bu verilere ait bir vatandaş bulunamadı",false);
    }

    @Override
    public Result sendMail(Long id){
        Student student = studentDao.getById(id);
        Mail mail = new Mail();

        redisService.setData(id);
        mail.setFromMail("cengdme@gmail.com");
        mail.setToMail(student.getMail());
        mail.setText("Doğrulama kodu: " + String.valueOf(redisService.getData(id)));
        mail.setSubject("Doğrulama Kodu");
        sendMail(mail);
        return new SuccesResult("Mail adresine doğrulama kodu gönderildi",true);
    }

    @Override
    public Result mailVerification(Long id, String mailCode){
        Student student = studentDao.getById(id);
        if(redisService.getData(id) == Integer.parseInt(mailCode)){
            student.setVerification(true);
            studentDao.save(student);
            return new SuccesResult("Doğrulama işlemi başarılı",true);
        }
        return new ErrorResult("Doğrulama işlemi başarısız",false);
    }

    @Override
    public Result findUniversityId(Long id){
        List<Student> students = studentDao.findByUniversity_Id(id);
        if (students !=  null && !students.isEmpty()){
            LogLevelSave(3, "Girilen üniversitede eğitim alan öğrenciler listelendi");
            return new SuccessDataResult("Girilen üniversitede eğitim alan öğrenciler listelendi",true,entityToDtoList(students,StudentMapper::toDto));
        }
        LogLevelSave(1,"Bu üniversiteye ait öğrenci bulunamadı");
        return new ErrorResult("Bu üniversiteye ait öğrenci bulunamadı",false);
    }

    @Override
    public List<Student> saveStudentAll(List<Student> students){
        return studentDao.saveAll(students);
    }

    @Override
    public Result updateStudent(Long id, StudentDTO studentDTO){
        DBQKPSPublicSoap client = new DBQKPSPublicSoap();
        Integer year = studentDTO.getBirthDate().getYear();
        Long tcNo = Long.parseLong(studentDTO.getTcNo());
        Map<String,String> updateUser = new HashMap<>();

        try {
            boolean isRealPerson = client.TCKimlikNoDogrula(tcNo,studentDTO.getName(),studentDTO.getSurName(),year);
            if (isRealPerson){
                Student editStudent = studentDao.getById(id);
                List<Student> students = studentDao.findAll();
                students.remove(editStudent);
                if (uniqueControl(students,studentDTO,"getTcNo") ||  uniqueControl(students,studentDTO,"getMail")){
                    LogLevelSave(1,"Mail veya kimlik nummarası benzersiz olmalıdır");
                    return new ErrorResult("Mail veya kimlik numarası benzersiz olmalıdır",false);
                }
                try {
                   updateUser.put("mail",studentDTO.getMail());
                   updateUser.put("name",studentDTO.getName());
                   updateUser.put("surName",studentDTO.getSurName());
                   userService.updateDormitoryUser(updateUser);

                   Student student = dtoToEntity(studentDTO,StudentMapper::toEntity);
                   student.setId(editStudent.getId());
                   editStudent=student;
                   studentDao.save(editStudent);
                   LogLevelSave(3,"Öğrenci güncelleme işlemi başarılı");
                   return new SuccessDataResult("Öğrenci güncelleme işlemi başarılı", true,entityToDto(editStudent,StudentMapper::toDto));
               } catch (Exception e) {
                   LogLevelSave(1,"Bu id değerine ait bir öğrenci bulunamadı.");
                   return new ErrorResult("Bu id değerine göre öğrenci bulunamadı",false);
               }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ErrorResult("Bu verilere ait bir vatandaş bulunamadı",false);
    }

    @Override
    public Result deleteStudent(Long id){
        try {
            Student deleteStudent = studentDao.getById(id);
            boolean check=false;
            List<Rental> rentalList = rentalDao.findAll();
            for (Rental rental : rentalList) {
                if (rental.getStudent().getId() == id) {
                    check= true;
                    rental.setDeleted(true);
                }
            }
            if (check){
                deleteStudent.setDeleted(true);
                studentDao.save(deleteStudent);
                LogLevelSave(3,"Öğrenci silindi ve öğrencinin kiraladığı alanlar silindi");
                return new SuccesResult("Öğrenci silindi ve öğrencinin kiraladığı alanlar silindi",true);
            }
            if (deleteStudent.getRoom() != null){
                Room room = roomDao.getById(deleteStudent.getRoom().getId());
                room.setManySize(room.getManySize() - 1);
                if (room.isFull()){
                    room.setFull(false);
                }
                roomDao.save(room);
            }
            userService.deleteDormitoryUser(deleteStudent.getMail());
            deleteStudent.setDeleted(true);
            studentDao.save(deleteStudent);
            LogLevelSave(3,"Öğrenci silme İşlemi başarılı.");
            return new SuccesResult("Öğrenci silindi",true);
        } catch (Exception e) {
            LogLevelSave(1,"Bu id değerine ait bir öğrenci bulunamadı.");
            return new ErrorResult("Bu id değerine göre öğrenci bulunamadı",false);
        }
    }
}





















