//package zw.co.sms.gsm.controller.admin;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import zw.co.sms.gsm.domain.*;
//import zw.co.sms.gsm.repository.CourseRepository;
//import zw.co.sms.gsm.util.GeneratePdfReport;
//
//import java.io.ByteArrayInputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/admin/generate")
//public class ExamEngineController {
//
//    @Autowired
//    private CourseRepository courseRepository;
//    @Autowired
//    QuestionRepository questionRepository;
//    @Autowired
//    private TopicRepository topicRepository;
//
//    private List<Question> easyQuestions = new ArrayList<>();
//    private List<Question> mediumQuestions = new ArrayList<>();
//    private List<Question> hardQuestions = new ArrayList<>();
//
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(Model model) {
//        List<Course> courses = courseRepository.findAll();
//        model.addAttribute("courses", courses);
//        model.addAttribute("examDTO", new ExamDTO());
//        return "admin/generate/list";
//    }
//
//
//
//    @RequestMapping(value = "/process", method = RequestMethod.GET)
//    public ResponseEntity<InputStreamResource> process(@RequestParam("courseName") String courseName) throws Exception{
//
//        System.out.println("------------------------------------------------" + courseName);
//        // create QuestionCollection object
//        QuestionCollection questionCollection = new QuestionCollection();
//
//        List<Topic> topics = topicRepository.findAllByCourseName(courseName);
//
//        int easyPercent = 20;
//        int mediumPercent = 70;
//        int hardPercent = 10;
//
//        //   List<Question> questionList = questionRepository.
//        System.out.println("===========================" + topics);
//
//        List<Question> questions = new ArrayList<>();
//        List<Question> questions1 = new ArrayList<>();
//        for (Topic t : topics) {
//            questions1 = questionRepository.findAllByTopicName(t.getName());
//            questions.addAll(questions1);
//
//        }
//
//        //  System.out.println("######################################============32222========"+questions);
//
//        //System.out.println("-----------------------"+questions.size());
//
//        for (Question q : questions) {
//
//            System.out.println("-------------------------------" + q.getPriority()
//            );
//            switch (q.getPriority()) {
//                case LOW:
//                    easyQuestions.add(q);
//                    break;
//                case AVERAGE:
//                    mediumQuestions.add(q);
//                    break;
//                case HIGH:
//                    hardQuestions.add(q);
//            }
//            Double totalLow = 0D;
//            for (Question lo : easyQuestions) {
//                totalLow += lo.getMarks();
//            }
//            Double totalMedium = 0D;
//            for (Question lo : mediumQuestions) {
//                totalMedium += lo.getMarks();
//            }
//            Double totalHigh = 0D;
//            for (Question lo : hardQuestions) {
//                totalHigh += lo.getMarks();
//            }
//
//            if (totalHigh + totalLow + totalMedium != 100) {
//                String format = "For percentEasy: %d, percentMedium: %d, percentHard: %d";
////                String text = String.format(format, totalLow.toString(), totalMedium.toString(), totalHigh.toString());
////                throw new IllegalArgumentException(text);
////            }
//                System.out.println("TTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+(totalHigh+totalLow+totalMedium));
//            List<Question> randomList = null;
//            int rand = (int) (100 * Math.random());
//            if (rand < totalLow) {
//                randomList = easyQuestions;
//            } else if (rand < totalLow + totalMedium) {
//                randomList = mediumQuestions;
//            } else {
//                randomList = hardQuestions;
//            }
//
//
//            // first get a random index to the list from 0 to < size
//            int size = randomList.size();
//            int listIndex = (int) (size * Math.random());
//            Question randomQuestion = randomList.get(listIndex);
//            System.out.println("RANDOMMMMMMMMMMM" + randomList.size());
//            System.out.println("TTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+(totalHigh+totalLow+totalMedium));
//        }
//    }
//
//
//        // we've now selected the correct List
//        // now get a random question from the list:
//
//
//        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(questions);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
//
//        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//
//        //return "admin/generate/list";
//    }
//
//
//}
