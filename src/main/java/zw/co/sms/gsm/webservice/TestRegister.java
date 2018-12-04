///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package zw.co.sms.gsm.webservice;
//
//import com.gmsstudentplatform.controller.dao.ExamResultsDao;
//import com.gmsstudentplatform.controller.dao.SmsRegistrationDao;
//
///**
// *
// * @author tmashakada
// */
//public class TestRegister {
//    public static void main(String [] args)
//    {
//        //"PGDE103""P1811124D"
//    String  studentpin="P1899745Q";
//    String coursecode="PGDE103";
//    double  coursecharge=100;
//    double newbalance=-100;
//
//   String rss=  ExamResultsDao.getStudentResult(studentpin);
//   System.out.println(rss+" "+"bbbb");
//     boolean  ischeckReupload=  SmsRegistrationDao.checkReupload(studentpin,coursecode);
//     System.out.println(ischeckReupload);
//     if(ischeckReupload ==false){
//      String result=  SmsRegistrationDao.registerCourse(studentpin, coursecode,coursecharge,newbalance);
//      System.out.println(result);
//     }else{
//           System.out.println("nnn");
//     }
//
//
//}}
