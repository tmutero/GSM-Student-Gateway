package zw.co.gsm.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zw.co.gsm.domain.*;
import zw.co.gsm.domain.sms.Message;
import zw.co.gsm.domain.sms.ServerResponse;
import zw.co.gsm.repository.*;
import zw.co.gsm.repository.sms.MessageRepository;
import zw.co.gsm.repository.sms.ResponseRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;

import java.net.MalformedURLException;

import java.net.ProtocolException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by ezinzombe on 3/28/18.
 */
@RestController
@RequestMapping("/sms")
public class SmsEndPoint {


    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudentAccountRepository studentAccountRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    String messageOutput = null;
    private String url = "http://192.168.43.1:1688/services/api/";


    @Scheduled(cron = "0/05 * * * * ?")
    public void saveSMS() {

        RestTemplate restTemplate = new RestTemplate();
        ServerResponse response = restTemplate.getForObject(url + "/messaging", ServerResponse.class);

        try {


            if (response != null) {
                List<Message> list = response.getMessages();
                Collections.sort(list);
                Message m = list.get(0);

                String message = m.getMessage();
                final String phone_num = m.getSender();
                String SPECIAL_CHARS_REGEX2 = "#";
                message = message.trim();


                String[] output = message.split(SPECIAL_CHARS_REGEX2);
                String purpose = output[0];

                if (Objects.equals(purpose, "230")) {

                    String regNumber = output[1];
                    Student student = studentRepository.findAllByRegNumber(regNumber);
                    Degree degree = degreeRepository.findAllByDegreeCode(student.getDegree().getDegreeCode());
                    List<String> course = courseRepository.findAllByDegree(degree);
                    String msgtostudent = "Your+Level+is" + "+" + student.getLevel().getName() + "Course+to+register+" + "-" + course.toString();
                    smsSend(msgtostudent, phone_num);
                }
                //Course Registration e.g 235#B1440945#CS102#BScCOMP
                if (Objects.equals(purpose, "235")) {
                    String studentReg = null;
                    String regNumber = output[1];
                    String courseCode = output[2];
                    String degreeCode = output[3];
                    Course course = courseRepository.findAllByCourseCode(courseCode);
                    Student student = studentRepository.findAllByRegNumber(regNumber);
                    Degree degree = degreeRepository.findAllByDegreeCode(degreeCode);

                    //Check Student Account if balance is greater than 300
                    List<StudentAccount> studentAccount = studentAccountRepository.findStudentAccountByDateCreated(student);
                    double amount = studentAccount.get(0).getAmount();
                    List<Registration> checkRegistration = registrationRepository.findAllByStudentAndCourse(student, course);

                    if (student == null || course == null) {
                        studentReg = "Incorrect+Registration+Number+Or+Course+Code+please+try+again.";
                        smsSend(studentReg, phone_num);
                    } else {
                        if (checkRegistration.size() == 0) {
                            if (amount > 250) {

                                Registration registration = new Registration();
                                registration.setCourse(course);
                                registration.setStudent(student);
                                registration.setPhoneNumber(phone_num);
                                registration.setActive(true);

                                registrationRepository.save(registration);

                                studentReg = "Your+Registration+was+successfully+created.+Send+Help+for+more+option";

                                smsSend(studentReg, phone_num);
                            } else {
                                studentReg = "Your+Registration+Have+Failed.+Your+Balance+is+" + "$" + amount + "+" + "instead+of+minimum+of+$250+required";
                                smsSend(studentReg, phone_num);
                            }

                        } else {
                            studentReg = courseCode + "+" + "has+already+registered+on+only+de-registration+allowed";
                            smsSend(studentReg, phone_num);
                        }

                    }
                }
//Show result e.g 240#B1440945#BScCOMP

                if (Objects.equals(purpose, "240")) {
                    String studentReg = null;
                    String regNumber = output[1];
                    String courseCode = output[2];
                    //    String degreeCode = output[3];


                    Student student = studentRepository.findAllByRegNumber(regNumber);
                    List<StudentAccount> studentAccount = studentAccountRepository.findStudentAccountByDateCreated(student);
                    double amount = studentAccount.get(0).getAmount();
                    if (amount > 250) {

                        List<Registration> registration = registrationRepository.findFirstByStudent(student);
                        ArrayList<String> s = new ArrayList<>();
                        for (Registration r : registration) {
                            s.add(r.getCourse().getName());
                        }
                        String sendD = "" + s + "";
                        smsSend(sendD, phone_num);
                    }

                }
//De-registration Part e.g 245#B1440945#CS101#password
                if (Objects.equals(purpose, "245")) {
                    String studentReg = null;
                    String regNumber = output[1];
                    String courseCode = output[2];
                    String password = output[3];
                    Course course = courseRepository.findAllByCourseCode(courseCode);
                    Student student = studentRepository.findAllByRegNumber(regNumber);
                    if (!Objects.equals(student.getPassword(), password)) {
                        studentReg = "Password+is_incorrect+please+enter+valid+one";
                        smsSend(studentReg, phone_num);
                    } else {
                        List<Registration> checkRegistration = registrationRepository.findAllByStudentAndCourse(student, course);
                        if (checkRegistration.size() != 0) {
                            Registration registration = new Registration();
                            registration.setCourse(course);
                            registration.setStudent(student);
                            registration.setPhoneNumber(phone_num);
                            registration.setActive(false);
                            Date newDate = new Date();
                            registration.setDateModified(newDate);
                            registrationRepository.save(registration);
                            studentReg = "Deregistration+of" + courseCode + "was+successfully+done.+Send+Help+for+more+option";
                            smsSend(studentReg, phone_num);
                        } else {
                            studentReg = courseCode + "is+already+de-registered+done.+Send+Help+for+more+option";
                            smsSend(studentReg, phone_num);
                        }

                    }
                }
            } else {
                System.out.println("server not reachable");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void smsSend(String msgtostudent, String phone_num) throws IOException {

        URL obj = new URL("http://192.168.43.1:1688/services/api/messaging/?To=" + phone_num + "&Message=" + msgtostudent);
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        int responseCode = postConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }
    }


}
