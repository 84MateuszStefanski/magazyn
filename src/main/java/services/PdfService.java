package services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class PdfService {

    public static void main(String[] args) throws FileNotFoundException {
        createPdfFile();
    }

    public static void createPdfFile() throws FileNotFoundException {

        String dest = "D:/Programowanie/AlphaJavaWorkSpace/Magazyn_faktura/sample.pdf";
        PdfWriter writer = new PdfWriter(dest);
        System.out.println("writer");
        PdfDocument pdfDoc = new PdfDocument(writer);
        System.out.println("pdfdoc");
        Document document = new Document(pdfDoc);
        System.out.println("document");
        String orderConfirmation = "Order confirmed";
        Paragraph paragraph = new Paragraph(orderConfirmation);
        document.add(paragraph);
        document.close();

    }
}
