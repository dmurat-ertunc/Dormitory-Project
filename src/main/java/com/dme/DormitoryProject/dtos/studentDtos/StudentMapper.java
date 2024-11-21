package com.dme.DormitoryProject.dtos.studentDtos;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.University;
import com.dme.DormitoryProject.repository.IRoomDao;
import com.dme.DormitoryProject.repository.IUniversityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class StudentMapper {
    @Autowired
    private static IUniversityDao universityDao;
    @Autowired
    private static IRoomDao roomDao;

    public StudentMapper(IUniversityDao universityDao, IRoomDao roomDao){
        this.universityDao=universityDao;
        this.roomDao=roomDao;
    }

    public static StudentDTO toDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurName(student.getSurName());
        dto.setTcNo(student.getTcNo());
        dto.setMail(student.getMail());
        dto.setBirthDate(student.getBirthDate());
        dto.setVerify(student.getVerification());
        dto.setRoomId(student.getRoom().getId());
        dto.setRoomNo(student.getRoom().getRoomNo());
        dto.setRoomSize(student.getRoom().getRoomSize());
        dto.setManySize(student.getRoom().getManySize());
        dto.setPerPersonPrice(student.getRoom().getPerPersonPrice());
        dto.setFull(student.getRoom().isFull());

        // Üniversite ID'lerini setlemek için
        Set<Long> universityIds = student.getUniversity().stream()
                .map(University::getId)
                .collect(Collectors.toSet());
        dto.setUniversityIds(universityIds);

        Set<String> univertsiyName = student.getUniversity().stream()
                .map(University::getName)
                .collect(Collectors.toSet());
        dto.setUniversityName(univertsiyName);

        dto.setScore(student.getScore());
        dto.setRemainingPermitHours(student.getRemainingPermitHours());



        return dto;
    }

    // DTO to Entity dönüşümü
    public static Student toEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }

        Student student = new Student();
        student.setName(dto.getName());
        student.setSurName(dto.getSurName());
        student.setTcNo(dto.getTcNo());
        student.setMail(dto.getMail());
        student.setBirthDate(dto.getBirthDate());
        student.setVerification(dto.isVerify());
        student.setRoom(roomDao.getById(dto.getRoomId()));

        // Üniversite ID'lerini setlemek için
        Set<University> universities = dto.getUniversityIds().stream()
                .map(universityDao::findById)  // Öğrencileri repository'den bul
                .filter(Optional::isPresent)       // Sadece var olanları al
                .map(Optional::get)                // Optional'ı aç ve Student'ı al
                .collect(Collectors.toSet());

        student.setUniversity(universities);

        student.setScore(dto.getScore());
        student.setRemainingPermitHours(dto.getRemainingPermitHours());

        return student;
    }
}
