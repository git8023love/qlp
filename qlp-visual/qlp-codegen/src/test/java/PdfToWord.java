import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class PdfToWord {

    public static void main(String[] args) {
        File file = new File("C:/Users/Administer/Documents/WeChat Files/h779043593/FileStorage/File/2019-10/asdsad.pdf");

        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pageNumber=doc.getNumberOfPages();//获取总页数

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("C:/Users/Administer/Desktop/asdsa/asdasd.doc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Writer writer = null;
        try {
            writer = new OutputStreamWriter(fos,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        PDFTextStripper stripper = null;
        try {
            stripper = new PDFTextStripper();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stripper.setSortByPosition(true);//排序
        stripper.setStartPage(1);//设置转换的开始页
        stripper.setEndPage(pageNumber);//设置转换的结束页

        try {
            stripper.writeText(doc,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
