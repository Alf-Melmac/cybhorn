package de.inhorn.cybhorn.invoice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.inhorn.cybhorn.model.Invoice;
import de.inhorn.cybhorn.model.Subscriber;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class PDFCreator {

	private static final String FILE_NAME = "./invoices";

	private static final float INDENTATION = 5f;
	private static final float SIZE_HEADER = 48f;
	private static final float SIZE_CELL = 15f;
	private static final float SIZE_TEXT = 10f;

	private static final float SMALL_HEIGHT = 30f;
	private static final float BIG_HEIGHT = 40f;

	public static void createPDF(Invoice invoice) {
		Document document = new Document(PageSize.A4);

		final Subscriber subscriber = invoice.getSubscriber();
		try (final FileOutputStream fos = new FileOutputStream(FILE_NAME + "-" + subscriber.getImsi() + ".pdf")) {
			PdfWriter.getInstance(document, fos);
			document.open();

			// Logo & companyname
			Image logo = Image.getInstance("src/main/resources/static/assets/img/iNhorn.PNG");
			logo.scaleToFit(logo.getWidth() / 2, logo.getHeight() / 2);
			Phrase cName = new Phrase(INDENTATION, "iNHorn & Co. KG\nBleichstr. 3\n33607 Bielefeld\nGermany", FontFactory.getFont(FontFactory.COURIER, SIZE_TEXT));
			Paragraph pLogo = new Paragraph();
			pLogo.add(logo);
			pLogo.add(cName);
			document.add(pLogo);

			// date
			Phrase date = new Phrase(INDENTATION, "Invoice date: " + LocalDate.now().toString(), FontFactory.getFont(FontFactory.COURIER, SIZE_TEXT));
			Paragraph pDate = new Paragraph();
			pDate.add(date);
			pDate.setAlignment(Element.ALIGN_RIGHT);
			document.add(pDate);

			// header
			Paragraph p = new Paragraph(new Phrase("INVOICE", FontFactory.getFont(FontFactory.COURIER, SIZE_HEADER)));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			//Name
			Phrase text = new Phrase(SIZE_HEADER,
					"Dear Mr/Mrs/Ms " + subscriber.getFirstName() + " " + subscriber.getLastName()
							+ ",\n\nbelow you will find a detailed account of your recent activities with our service.",
					FontFactory.getFont(FontFactory.COURIER, SIZE_TEXT));
			Paragraph name = new Paragraph();
			name.add(text);
			document.add(name);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			// basic infos
			PdfPCell cell;
			PdfPTable table = new PdfPTable(2);
			cell = new PdfPCell(new Phrase(INDENTATION, "Subscription Type", FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, subscriber.getSubscription().getName(), FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, "Basic Fee in €", FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, Integer.toString(subscriber.getSubscription().getBasicFee()), FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, "Voice Minutes included", FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, Integer.toString(subscriber.getSubscription().getSecondsIncluded()/60), FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, "Total Data Volume Used\n(in MB)", FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(BIG_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, String.valueOf(subscriber.getDataUsed()), FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(BIG_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, "Total: Voice Minute Used", FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, String.valueOf(subscriber.getSecondsCalled() / 60), FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			table.addCell(cell);
			document.add(table);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			// total charges
			PdfPTable tableTotalCharges = new PdfPTable(2);
			cell = new PdfPCell(new Phrase(INDENTATION, "Total Charge in €", FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			tableTotalCharges.addCell(cell);
			cell = new PdfPCell(new Phrase(INDENTATION, Double.toString(invoice.getTotalCosts()), FontFactory.getFont(FontFactory.COURIER, SIZE_CELL)));
			cell.setFixedHeight(SMALL_HEIGHT);
			tableTotalCharges.addCell(cell);
			document.add(tableTotalCharges);
			document.close();

			subscriber.reset();
		} catch (DocumentException | IOException e) {
			log.error("Error on file creation", e);
		} finally {
			log.info("Invoice for {}", subscriber.getImsi());
		}
	}
}
