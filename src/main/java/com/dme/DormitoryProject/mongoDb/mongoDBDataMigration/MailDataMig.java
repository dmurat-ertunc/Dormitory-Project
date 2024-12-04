//package com.dme.DormitoryProject.mongoDb.mongoDBDataMigration;
//
//import com.dme.DormitoryProject.entity.Mail;
//import com.dme.DormitoryProject.mongoDb.mongoDBEntity.MailMg;
//import com.dme.DormitoryProject.mongoDb.mongoDBRepository.IMailMgDao;
//import com.dme.DormitoryProject.repository.IMailDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MailDataMig {
//
//    private IMailMgDao mailMgDao;
//    private IMailDao mailDao;
//
//    @Autowired
//    public MailDataMig(IMailDao mailDao, IMailMgDao mailMgDao){
//        this.mailDao=mailDao;
//        this.mailMgDao=mailMgDao;
//    }
//    @Scheduled(cron = "00 35 11 * * ?")
//    public void migration(){
//        List<Mail> mailList = mailDao.findAll();
//
//        for (Mail mail : mailList){
//            if (!mail.isThrowMongo()){
//                MailMg mailMg = new MailMg();
//                mailMg.setMailId(mail.getId());
//                mailMg.setToMail(mail.getToMail());
//                mailMg.setFromMail(mail.getFromMail());
//                mailMg.setText(mail.getText());
//                mailMg.setSubject(mail.getSubject());
//                mailMg.setAddDate(mail.getAddDate());
//                mailMg.setDeleted(mail.isDeleted());
//                mailMg.setThrowMongo(mail.isThrowMongo());
//
//                mail.setThrowMongo(true);
//
//                mailMgDao.save(mailMg);
//                mailDao.save(mail);
//            }
//        }
//
//
//
//    }
//}
