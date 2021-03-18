package de.inhorn.cybhorn.invoice;

import de.inhorn.cybhorn.model.Subscriber;

import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class PDFCreator {

    private final String FILE_NAME = System.getProperty("user.dir"); //TODO wohin standardmaeßig speichern

    public void createPDF(Invoice invoice) {
        Document document = new Document(PageSize.A4);

        float indentation = 5f;
        float sizeHeader = 48f;
        float sizeCell = 15f;
        float sizeText = 10f;

        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();

            // Logo & companyname
            Image logo = Image.getInstance("src/main/resources/static/assets/img/iNhorn.PNG");
            logo.scaleToFit(logo.getWidth() / 2, logo.getHeight() / 2);
            Phrase cName = new Phrase(indentation, "iNHorn & Co. KG\nBleichstr. 3\n33607 Bielefeld\nGermany", FontFactory.getFont(FontFactory.COURIER, sizeText));
            Paragraph pLogo = new Paragraph();
            pLogo.add(logo);
            pLogo.add(cName);
            document.add(pLogo);

            // date
            Phrase date = new Phrase(indentation, "Invoice date: " +LocalDate.now().toString(), FontFactory.getFont(FontFactory.COURIER, sizeText));
            Paragraph pDate = new Paragraph();
            pDate.add(date);
            pDate.setAlignment(Element.ALIGN_RIGHT);
            document.add(pDate);

            // header
            Paragraph p = new Paragraph(new Phrase("INVOICE", FontFactory.getFont(FontFactory.COURIER, sizeHeader)));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            //Name
            Phrase text = new Phrase(sizeHeader,
                    "Dear Mr/Mrs/Ms" + invoice.getSubscriber().getFirstName() + " " + invoice.getSubscriber().getLastName()
                            +",\n\nbelow you will find a detailed account of your recent activities with our service.",
                    FontFactory.getFont(FontFactory.COURIER, sizeText));
            Paragraph name = new Paragraph();
            name.add(text);
            document.add(name);

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            // basic infos
            PdfPCell cell;
            PdfPTable table = new PdfPTable(2);
            cell = new PdfPCell(new Phrase(indentation, "Subscription Type", FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(30f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(indentation, invoice.getSubscriber().getSubscription().getName(), FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(30f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(indentation, "Total Data Volume Used\n(in MB)", FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(40f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(indentation, String.valueOf(invoice.getSubscriber().getDataUsed()), FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(40f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(indentation, "Total: Voice Minute Used", FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(30f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(indentation, String.valueOf(invoice.getSubscriber().getSecondsCalled()/60), FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(30f);
            table.addCell(cell);
            document.add(table);

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            // total charges
            PdfPTable tableTotalCharges = new PdfPTable(2);
            cell = new PdfPCell(new Phrase(indentation, "Total Charge in €", FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(30f);
            tableTotalCharges.addCell(cell);
            cell = new PdfPCell(new Phrase(indentation, String.valueOf(invoice.getTotalcosts()), FontFactory.getFont(FontFactory.COURIER, sizeCell)));
            cell.setFixedHeight(30f);
            tableTotalCharges.addCell(cell);
            document.add(tableTotalCharges);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace(); //TODO Fehlerbehandlung
        }
    }
}
