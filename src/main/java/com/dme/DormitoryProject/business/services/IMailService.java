package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.response.Result;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IMailService {
    void sendMail(String mail,Long code);
    String sendMultiMediaMail();
    void permitRentalMailSending(String mail, String sportArea, LocalTime startTime, LocalTime endTime);
    void rejectedRentalMailSending(String mail, String sportArea, LocalTime startTime, LocalTime endTime);

}
