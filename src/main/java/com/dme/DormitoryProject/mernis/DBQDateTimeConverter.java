package com.dme.DormitoryProject.mernis;

//------------------------------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 9.3.0.0
//
// Created by Quasar Development 
//
// This class has been generated for test purposes only and cannot be used in any commercial project.
// To use it in commercial project, you need to generate this class again with Premium account.
// Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
//
// Licence: 8023C7DDC7B75E103269AE3120C605CC27F5BC61C7FBC58700C86149088A0A063C43700ED546166DD49750F6E84EEFDEC28C34522C60739A74E31159E6F6BBBA
//------------------------------------------------------------------------
import java.util.Date;


public interface DBQDateTimeConverter
{
    java.util.Date convertDateTime(String strDate);
    java.util.Date convertTime(String strDate);
    java.util.Date convertDate(String strDate);
    String convertDuration(String value);
    String getStringFromDateTime(Date value);
    String getStringFromDate(Date value);
    String getStringFromTime(Date value);
    String getStringFromDuration(String value);
}