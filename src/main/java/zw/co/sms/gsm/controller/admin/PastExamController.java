package zw.co.sms.gsm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/pastExam")
public class PastExamController extends HttpServlet{

    @Autowired
    private ServletContext servletContext;


    private static final String DIRECTORY = "/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/exams";
//    private static final String DEFAULT_FILE_NAME = "java-tutorial.pdf";

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<String> results = new ArrayList<String>();
        File[] files = new File("/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/exams").listFiles();
//If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPP"+results);
        model.addAttribute("files", results);
        return "admin/pastExams/list";
    }

    @GetMapping("/show")
    public String getPdf(){
        ParagraphPdf c1=new ParagraphPdf();
        c1.m1();
        return "showPdf";
    }


    @GetMapping("/download/{fileName}")
    public StreamingResponseBody getSteamingFile(HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {


        System.out.println("========================="+fileName);


        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Content-Disposition", "attachment; filename=\"questions.pdf\"");

        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        InputStream inputStream = new FileInputStream(new File("/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/exams/"+fileName));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
    }


}
