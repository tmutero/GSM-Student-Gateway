///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package zw.co.sms.gsm.webservice;
//
//import com.gmsstudentplatform.controller.dao.ExamResultsDao;
//import com.gmsstudentplatform.controller.dao.SmsDao;
//import com.gmsstudentplatform.controller.dao.SmsRegistrationDao;
//import com.gmsstudentplatform.controller.dao.StudentBalanceDao;
//import com.gmsstudentplatform.controller.dao.StudentDao;
//import com.gmsstudentplatform.controller.model.Sms;
//
//import javax.jws.WebService;
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//
///**
// *
// * @author tmashakada
// */
//@WebService(serviceName = "GMSWebService")
//public class GMSWebService {
//
//    /**
//     * This is a sample web service operation
//     * @param msg
//     * @return
//     */
//
//    @WebMethod(operationName = "gmswebserviceinter")
//    public String gmswebserviceinter(@WebParam(name = "originalmsg") String msg) {
//
//
//        String  mySmsGatewayWebServicereturn=null;
//
//       // String txt=msg;
//        String SPECIAL_CHARS_REGEX = "~";
//     //   String msg_body2 = orignalmsg.toLowerCase();
//           int specials =msg.split(SPECIAL_CHARS_REGEX, -1).length - 1;
//             String[] partss = msg.split("~");
//             String  mobilenumber = partss[0];
//             String originalmsg = partss[1]; // 004
//            //msgtype = msgtype.replaceAll("\\s+", "");
//             String time = partss[2];
//          System.out.println(mobilenumber+" "+originalmsg +" "+time+" "+specials);
//
//              Sms sms =new Sms();
//              sms.setMobilenumber(mobilenumber);
//              sms.setOriginalmsg(originalmsg);
//              sms.setTime(time);
//              SmsDao smsDao=new SmsDao();
//            int msgid=  smsDao.saveRecievedMsg(sms);
//            System.out.println(  "MSG SAVE STATUS "+msgid);
//
//            if(msgid>0){
//          String SPECIAL_CHARS_REGEX2 = "#";
//     //   String msg_body2 = orignalmsg.toLowerCase();
//            originalmsg=originalmsg.trim();
//           int specials2 =originalmsg.split(SPECIAL_CHARS_REGEX2, -1).length - 1;
//           System.out.println(specials2+" mmm");
//           if(specials2>0){
//
//              String[] partss2 = originalmsg.split("#");
//              String msgtype2 = partss2[0]; // 004
//              msgtype2 = msgtype2.replaceAll("\\s+", "");
//              String studentpin = partss2[1];
//              studentpin = studentpin.replaceAll("\\s+", "");
//              String myvistpass = partss2[2];
//             if (msgtype2.equalsIgnoreCase("230")) {
//
//                 double balance= StudentBalanceDao.getStudentBalance(studentpin);
//               //  INSERT INTO gmsdb.sentsms( mobilenumber, sentmsg, studentpin, msgtype, originalmsgid) VALUES (?, ?, ?, ?, ?, ?, ?);
//                 String msgtostudent="Your Balance is  " +balance;
//                 //save before sending to student
//                    Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg(msgtostudent);
//                    smssent.setStudentpin(studentpin);
//                    smssent.setMsgtype("Student Balance");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//
//                     mySmsGatewayWebServicereturn=msgtostudent;
//
//             }else if(msgtype2.equalsIgnoreCase("234")){
//                 String studentresults=null;
//                 boolean isvalidstudentpin=StudentDao.isValidStudentPin(studentpin);
//                       if(isvalidstudentpin){
//                 double balance= StudentBalanceDao.getStudentBalance(studentpin);
//
//                 if(balance <10){
//                     studentresults=ExamResultsDao.getStudentResult(studentpin);
//                       String[]  studentresultsparts = studentresults.split("#");
//                     String msgtostudent = studentresultsparts[0];
//                     String msgtosave = studentresultsparts[1];
//                    Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg(msgtosave);
//                    smssent.setStudentpin(studentpin);
//                    smssent.setMsgtype("Exam Results");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//                    mySmsGatewayWebServicereturn=msgtostudent;
//                 }else{
//                     studentresults="Your Results Have Been Supressed . Your Balance is  "+balance;
//                 //save before sending to student
//                    Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg(studentresults);
//                    smssent.setStudentpin(studentpin);
//                    smssent.setMsgtype("Exam Results");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//                    mySmsGatewayWebServicereturn=studentresults;
//                 }
//                   }else{
//                           studentresults="Student Pin You Entered is Not Valid  "+studentpin;
//
//                           Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg(studentresults);
//                    smssent.setStudentpin(studentpin);
//                    smssent.setMsgtype("Exam Results");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//                    mySmsGatewayWebServicereturn=studentresults;
//                       }
//
//
//             }else if(msgtype2.equalsIgnoreCase("235")){
//                 String registrationmsgtostudent=null;
//                 //String registrationmsgtosave=null;
//                if(specials2==3){
//                     String coursecode = partss2[3];
//                    //getcoursecharge
//                    //getstudentbalance
//                   double coursecharge =StudentBalanceDao.getCourseCharge(coursecode);
//                   if(coursecharge>0.00){
//                       boolean isvalidstudentpin=StudentDao.isValidStudentPin(studentpin);
//                       if(isvalidstudentpin){
//                        double balance= StudentBalanceDao.getStudentBalance(studentpin);
//                        double newbalance=balance+coursecharge;
//                        if(newbalance<1){
//                         boolean ischeckReupload=   SmsRegistrationDao.checkReupload(studentpin,coursecode);
//                         if(ischeckReupload==false){
//                              String result=SmsRegistrationDao.registerCourse(studentpin, coursecode,coursecharge,newbalance);
//                              registrationmsgtostudent =result;
//                         }else{
//                             registrationmsgtostudent="Course "+coursecode+" "+"Already Registered";
//                         }
//
//
//                        }else {
//                             registrationmsgtostudent="Insuffient balance To register Course "+coursecode;
//                        }
//                       }else{
//                           registrationmsgtostudent="Student Pin You Entered is Not Valid  "+studentpin;
//                       }
//                   }else{
//                       registrationmsgtostudent="Wrong Course Code "+coursecode;
//                   }
//
//                }else{
//                    registrationmsgtostudent="Your  Msg is in the Wrong Format Send Help";
//               //     registrationmsgtosave="Your  Msg is in the Wrong Format";
//                }
//
//                   Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg( registrationmsgtostudent);
//                    smssent.setStudentpin(studentpin);
//                    smssent.setMsgtype("SMS Registration");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//                   mySmsGatewayWebServicereturn=registrationmsgtostudent ;
//
//
//             }else if(msgtype2.equalsIgnoreCase("236")){
//               String registeredcourses=  SmsRegistrationDao.getRegisteredCourses(studentpin);
//               String registeredcoursestostudent=null;
//               if(registeredcourses!=null){
//                   registeredcoursestostudent=registeredcourses;
//               }else{
//                   registeredcoursestostudent="You are not Registered in any Course";
//               }
//
//
//                    Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg( registeredcoursestostudent);
//                    smssent.setStudentpin(studentpin);
//                    smssent.setMsgtype("SMS Registration");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//
//                mySmsGatewayWebServicereturn= registeredcoursestostudent ;
//
//              } else if(msgtype2.equalsIgnoreCase("237")){
//                  String deregisterresult=null;
//                  if(specials2==3){
//                     String coursecode = partss2[3];
//                  boolean isvalidstudentpin=StudentDao.isValidStudentPin(studentpin);
//                       if(isvalidstudentpin){
//
//                            boolean ischeckReupload=   SmsRegistrationDao.checkReupload(studentpin,coursecode);
//                            if(ischeckReupload==true){
//                             double balance= StudentBalanceDao.getStudentBalance(studentpin);
//                             SmsRegistrationDao smsRegistrationDao=new SmsRegistrationDao();
//                            String  refnumber=String.valueOf(msgid);
//                             deregisterresult=smsRegistrationDao.deregisterCourse(studentpin, coursecode, refnumber, balance);
//
//                            }else{
//                                 deregisterresult=coursecode+" "+" is not Registered therefore it can not De-Registered";
//                            }
//                       }else{
//                         deregisterresult="Student Pin You Entered is Not Valid  "+studentpin; ;
//                       }
//                  }else{
//                      deregisterresult="Your  Msg is in the Wrong Format Send Help";
//                  }
//
//                   Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg(deregisterresult);
//                    smssent.setStudentpin(studentpin);
//                    smssent.setMsgtype("SMS Course DeRegistration");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//                mySmsGatewayWebServicereturn= deregisterresult ;
//              }
//             else{
//
//
//                     String wrongmsg="Your MSG is the Wrong format Sent Help" ;
//                    Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg( wrongmsg);
//                    smssent.setStudentpin(mobilenumber);
//                    smssent.setMsgtype("Wrong format MSG");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//                    mySmsGatewayWebServicereturn=wrongmsg ;
//
//
//
//
//
//             }
//
//           }else{
//
//                    String wrongmsg;
//                      if(originalmsg.equalsIgnoreCase("Help")){
//                        wrongmsg="1.Balance Enquiry eg 230#pin#password \n 2.Results Enquiry 234#pin#password \n 3.Register Course eg 235#pin#password#coursecode  \n 4.De register Course eg  237#pin#password#coursecode ";
//                    }else if(originalmsg.equalsIgnoreCase("200")){
//                        wrongmsg="1.Balance Enquiry eg 230#pin#password \n 2.Results Enquiry 234#pin#password \n 3.Register Course eg 235#pin#password#coursecode  \n 4.De register Course eg  237#pin#password#coursecode ";
//                    }else{
//                        wrongmsg="Wrong Message format Send Help";
//                    }
//                    Sms smssent=new Sms();
//                    smssent.setMobilenumber(mobilenumber);
//                    smssent.setSentmsg( wrongmsg);
//                    smssent.setStudentpin(mobilenumber);
//                    smssent.setMsgtype("General SMS");
//                    smssent.setOriginalmsgid(String.valueOf(msgid));
//                    SmsDao.savesentMsg(smssent);
//                    mySmsGatewayWebServicereturn=wrongmsg ;
//           }
//
//         }else{
//                mySmsGatewayWebServicereturn="ERROR 102 Please Try Later" ;
//            }
//        return mySmsGatewayWebServicereturn ;
//    }
//}
