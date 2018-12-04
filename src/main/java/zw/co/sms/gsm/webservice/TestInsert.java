///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package zw.co.sms.gsm.webservice;
//
//import com.gmsstudentplatform.controller.dao.DBConnection;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// *
// * @author tmashakada
// */
//public class TestInsert {
//    public static void main(String [] args) throws SQLException
//    {
//        //SmsDao smsd=newS SmsDao()
//        String sql="SELECT studentcode, study_code, subject_code, code,  decision,serial_number, exam_session\n" +
//"  FROM aris.myvista_currentresults_assingment_view  where studentcode in('P0013569J','P1799775R','P1799745Q','P1799054K','P000054W','P000778F','P000786P','P0010499L','P0011124D','P0011218M','P0011616Y','P0012452E','P0012525X')";
//
//     String sql2="SELECT distinct subject_code, subject_description\n" +
//"  FROM aris.myvista_currentresults_view where studentcode in('P0013569J','P1799775R','P1799745Q','P1799054K','P000054W','P000778F','P000786P','P0010499L','P0011124D','P0011218M','P0011616Y','P0012452E','P0012525X');" ;
//     Connection conn = DBConnection.getConnectionPostgreSQLARIS();
//    Connection conn2 = DBConnection.getConnectionPostgreSQLARIS();
//            PreparedStatement ptmstate = conn.prepareStatement(sql2);
//            ResultSet rs = ptmstate.executeQuery();
//
//            while(rs.next()){
//                String coursecode=rs.getString("subject_code");
//                 String coursedescription=rs.getString("subject_description");
//                  double courseamoiunt=100;
//               String insert="INSERT INTO gmsdb.course(coursecode, coursedescription,  courseamount)    VALUES (?, ?, ?);";
//                PreparedStatement ptmstate2 = conn2.prepareStatement(insert);
//                      ptmstate2.setString(1, coursecode);
//                    ptmstate2.setString(2, coursedescription);
//                    ptmstate2.setDouble(3, courseamoiunt);
//
//                    int result=    ptmstate2.executeUpdate();
//                   if(result>0){
//                      }
////             System.out.println("P18"+part2);
////               String insert="INSERT INTO gmsdb.examresults(studentpin, studycode, coursecode, code, decision, serial_number, exam_session)\n" +
////"    VALUES (?, ?, ?, ?, ?, ?, ?);";
////                   PreparedStatement ptmstate2 = conn2.prepareStatement(insert);
////                    ptmstate2.setString(1, "P18"+part2);
////                    ptmstate2.setString(2, study_code);
////                    ptmstate2.setString(3, subject_code);
////                    ptmstate2.setString(4, code);
////                    ptmstate2.setString(5, decision);
////                    ptmstate2.setString(6, serial_number);
////                    ptmstate2.setString(7, exam_session);
////                 int result=    ptmstate2.executeUpdate();
////                  if(result>0){
////
////                  }
//               ptmstate2.close();
//            }
//             ptmstate.close();
//    conn.close();
//    conn2.close();
//    }
//
//}
