package zw.co.sms.gsm.controller.admin;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ParagraphPdf {
    public void m1(){
        Document document=new Document();

        try{
            PdfWriter.getInstance(document, new FileOutputStream("ParagraphTest.pdf"));

            document.open();

            Paragraph p1=new Paragraph();
            Paragraph p2=new Paragraph();
            Paragraph p3=new Paragraph();

//            p1.setSpacingBefore(10);
            p1.setAlignment(Element.ALIGN_LEFT);
            p1.setIndentationRight(30);

            p2.setSpacingBefore(30);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setIndentationLeft(20);
            p2.setIndentationRight(20);
            p2.setSpacingAfter(30);

            p3.setAlignment(Element.ALIGN_RIGHT);
            p3.setIndentationLeft(30);

            for (int i=1;i<=20;i++){
                Chunk chunk=new Chunk("This is sentence "+i+".");
                p1.add(chunk);
                p2.add(chunk);
                p3.add(chunk);
            }

            document.add(p1);
            document.add(p2);
            document.add(p3);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}