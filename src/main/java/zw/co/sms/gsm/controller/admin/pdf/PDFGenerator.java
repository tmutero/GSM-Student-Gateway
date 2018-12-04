//package zw.co.sms.gsm.controller.admin.pdf;
//
//import java.io.*;
//import java.util.List;
//import java.util.Random;
//
//import com.itextpdf.text.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.itextpdf.text.pdf.PdfWriter;
//
//public class PDFGenerator {
//
//    private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
//
//    public static ByteArrayInputStream examPDFReport(List<User> questions) throws IOException {
//        Document document = new Document();
//        /** Path to the resulting PDF file. */
//
//
//
//        String examName= questions.stream().findFirst().get().getTopic().getCourse().getName();
//        String examCode= questions.stream().findFirst().get().getTopic().getCourse().getCourseCode();
//        Random random = new Random();
//        final String RESULT
//                = "/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/exams/"+examName+random.nextInt(15)+".pdf";
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//
//            PdfWriter.getInstance(document, out);
//            PdfWriter.getInstance(document, new FileOutputStream(RESULT));
//
//            document.open();
//
//            // Add Text to PDF file ->
//            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
//            Paragraph para = new Paragraph( "Women University In Africa:" +" "+examName+" " + "2018 December Exams", font);
//            para.setAlignment(Element.ALIGN_CENTER);
//            document.add(para);
//            Image image = Image.getInstance("/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/static/images/logo.jpeg");
//            image.setAbsolutePosition(200f, 550f);
//
//            document.add(image);
//
//            para.setSpacingAfter(800f);
//            document.add(Chunk.NEWLINE);
//            document.add(new Paragraph("EXAM NAME : "+ examName));
//            document.add(new Paragraph("COURSE CODE : "+ examCode));
//            document.add(new Paragraph("TIME ALLOWED : 3 HOURS"));
//            document.add(new Paragraph("SPECIAL REQUIREMENTS: NONE"));
//
//
//
//
//            for (User question: questions) {
//
//                int position=questions.indexOf(question);
//                String s1 = 1 +position +" "+question.getNotes()+" "+"[ Marks :"+ question.getMarks().toString()+" ]";
//                Paragraph paragraph1 = new Paragraph(s1);
//                paragraph1.setSpacingBefore(20f);
//                System.out.println("==========================="+s1);
//                document.add(paragraph1);
//            }
//
//            document.close();
//        }catch(DocumentException e) {
//            logger.error(e.toString());
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//
//}
