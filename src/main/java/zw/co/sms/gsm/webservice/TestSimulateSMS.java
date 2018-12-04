///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package zw.co.sms.gsm.webservice;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//
//
///**
// *
// * @author Corium Electronix
// */
//public class TestSimulateSMS {
//
//    public static void main(String[] args){
//     SimulateGMSWebService simulateGMSWebService=new SimulateGMSWebService();
//   String datetime=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//    //String mobilenumber="0775233306";
//   //String msg=mobilenumber+"~"+"230#P1811124D#test"+"~"+datetime;
//
//   //Registration Test
//  //  String mobilenumber="0779256913";
//  // String msg=mobilenumber+"~"+"235#P1811124SD#test#BITH121"+"~"+datetime;
//   //Get Results;
//   //String mobilenumber="0779256913";
//  // String msg=mobilenumber+"~"+"234#P180786P#test"+"~"+datetime;
//   //TimeTable:
//  //String mobilenumber="0779256913";
//  //String msg=mobilenumber+"~"+"234#P180786P#test"+"~"+datetime;
//  //DeregisterCourses;
//   String mobilenumber="0779256913";
//  String msg=mobilenumber+"~"+"235#P1899745Q#test#BITH102"+"~"+datetime;
// //Get Registered Courses
//
//   String result=  simulateGMSWebService.gmswebserviceinter(msg);
//   System.out.println("SMS Sent To Student "+result);
//
//    }
//
//}
