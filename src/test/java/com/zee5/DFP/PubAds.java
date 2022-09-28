package com.zee5.DFP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.excel.ExcelFunctions;
import com.extent.ExtentReporter;

public class PubAds {

	ArrayList<String> Prerollai = new ArrayList<String>();
	ArrayList<String> Midrollai = new ArrayList<String>();
	ArrayList<String> Postrollai = new ArrayList<String>();
	XSSFWorkbook myExcelBook;
	XSSFSheet myExcelSheet;
	static ExtentReporter extent  = new ExtentReporter();

	String xlPath = System.getProperty("user.dir") + "\\DFPExcelDump\\DFP104.xlsx";
	String sheet = "Sheet1";
	static int rc = 0;
	int colNumber = 1;

//	static String[] keys = { "iu", "env", "ad_rule", "label", "gdfp_req", "output", "sz", "unviewed_position_start",
//			"video_id", "video_title", "episode_number", "show_id", "season_id", "asset_subtype", "audio_language",
//			"collection_id", "content_creator", "genre", "channel_id", "app_version", "description_url", "url",
//			"correlator", "hl", "js", "an", "msid", "omid_p", "ai" };
	
	static String[] keys = {"iu",
			"env",
			"gdfp_req",
			"ad_rule",
			"output",
			"sz",
			"unviewed_position_start",
			"url",
			"correlator",
			"vpa",
			"vpmute",
			"description_url",
			"user-agent",
			"referer",
			"origin",
			"vis",
			"sdr",
			"adk",
			"cust_params",
			"gender",
			"age",
			"user_type",
			"show_name",
			"app_version",
			"moatactive",
			"content_creator",
			"ppid",
			"label"};

	static String[] headerkeys = { "Keys", "VMAP", "VAST-PreRoll", "VAST-MidRoll", "VAST-PostRoll" };

	public void readDocumnet() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		ArrayList<String> AllCalls = new ArrayList<String>();
//		System.out.println(System.getProperty("user.dir")+"//"+CharlesConfigure.charlesName);
		
		File file = new File("E:\\Zee5_Project\\Zee5\\zee5_updated\\DFPContentlog_173920111213.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setFeature("http://xml.org/sax/features/namespaces", false);
		dbf.setFeature("http://xml.org/sax/features/validation", false);
		dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		oneTag(doc);
		NodeList nodeList = doc.getElementsByTagName("value");
		for (int itr = 0; itr < nodeList.getLength(); itr++) {
			Node node = nodeList.item(itr);
			if (node.getTextContent().contains("iu=")
					|| node.getTextContent().contains("ai=") && node.getTextContent().contains("")) {
				AllCalls.add(node.getTextContent());
			}
		}
		for (int i = 0; i < AllCalls.size(); i++) {
			String[] split = AllCalls.get(i).replaceAll("%2C", ",").replaceAll("%3D", "=").replaceAll("%26", "&")
					.replace("amp;", "").split("&");
			for (int j = 0; j < split.length; j++) {
				String Key = "";
				String Value = "";
				Key = split[j].split("=")[0];
				if (Key.contains("?iu") || Key.contains("?ai")) {
					Key = Key.split("\\?")[1];
				}
				if (split[j].split("=").length > 1) {
					Value = split[j].split("=")[1];
				}
				if (!AllCalls.get(i).contains("adview?ai=")) {
					writeKeyValue(AllCalls.get(i), Key, Value);
				}
			}
		}
	}

	public void oneTag(Document doc) {
		ArrayList<String> eachOne = new ArrayList<String>();

		NodeList node = doc.getElementsByTagName("transaction");
		for (int i = 0; i < node.getLength(); i++) {
			eachOne.add(node.item(i).getTextContent());
		}

		for (int i = 0; i < eachOne.size(); i++) {
			if (eachOne.get(i).contains("pagead/conversion/?ai=")) {
				if (eachOne.get(i).contains("Preroll")) {
					Pattern p = Pattern.compile("/?ai=([a-zA-Z0-9/_/-]{0,1000})");
					Matcher m = p.matcher(eachOne.get(i));
					m.find();
					Prerollai.add(m.group(0).split("ai=")[1]);
				}

				if (eachOne.get(i).contains("Midroll")) {
					Pattern p = Pattern.compile("/?ai=([a-zA-Z0-9/_/-]{0,1000})");
					Matcher m = p.matcher(eachOne.get(i));
					m.find();
					Midrollai.add(m.group(0).split("ai=")[1]);
				}
				if (eachOne.get(i).contains("Postroll")) {
					Pattern p = Pattern.compile("/?ai=([a-zA-Z0-9/_/-]{0,1000})");
					Matcher m = p.matcher(eachOne.get(i));
					m.find();
					Postrollai.add(m.group(0).split("ai=")[1]);
				}
			}
		}
	}

	public void writeKeyValue(String URL, String Key, String Value) throws InterruptedException {

		String Previousdata = "";
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlPath));
			FileOutputStream output = new FileOutputStream(xlPath);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet);
			if (URL.contains("?ai=")) {
				cont(URL);
			}
			if (URL.contains("Preroll")) {
				colNumber = 2;
			} else if (URL.contains("Postroll")) {
				colNumber = 4;
			} else if (URL.contains("Midroll")) {
				colNumber = 3;
			}

			rc = myExcelSheet.getLastRowNum();
			for (int noRow = 0; noRow < rc; noRow++) {

				if (myExcelSheet.getRow(noRow + 1).getCell(0).toString().equals(Key)) {

					Cell celll = myExcelSheet.getRow(noRow + 1).getCell(colNumber);

					if (celll == null) {
					} else {
						Previousdata = myExcelSheet.getRow(noRow + 1).getCell(colNumber).toString();
					}

					XSSFRow row = myExcelSheet.getRow((noRow + 1));
					if (row == null) {
						row = myExcelSheet.createRow((noRow + 1));
					}
					Cell cell = null;
					// Update the value of cell
					if (cell == null) {
						cell = row.createCell(colNumber);
					}
					cell.setCellValue(Previousdata + "\n" + Value);
					break;
				}
			}
			myExcelBook.write(output);
			myExcelBook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write() {
		try {

			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlPath));
			FileOutputStream output = new FileOutputStream(xlPath);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet);

			XSSFCellStyle cellStyle = myExcelBook.createCellStyle();
			XSSFFont cellFont = myExcelBook.createFont();
			cellStyle.setWrapText(true);
			cellFont.setBold(true);
			cellFont.setFontHeightInPoints((short) 15);

			cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			cellStyle.setFillPattern(FillPatternType.ALT_BARS);
			cellStyle.setFont(cellFont);
			cellStyle.setBorderLeft(BorderStyle.THICK);
			cellStyle.setBorderRight(BorderStyle.THICK);
			cellStyle.setBorderTop(BorderStyle.THICK);
			cellStyle.setBorderBottom(BorderStyle.THICK);

			XSSFFont cellColumnFont = myExcelBook.createFont();
			cellColumnFont.setColor(IndexedColors.RED.getIndex());
			cellColumnFont.setFontHeightInPoints((short) 11);

			XSSFCellStyle cellColumnStyle = myExcelBook.createCellStyle();
			cellColumnStyle.setWrapText(true);
			cellColumnStyle.setFont(cellColumnFont);
			XSSFRow row = myExcelSheet.createRow(0);
			if (row == null) {
				row = myExcelSheet.createRow(0);
			}
			for (int i = 0; i < headerkeys.length; i++) {
				Cell cell1 = row.createCell(i);
				cell1.setCellValue(headerkeys[i]);
				cell1.setCellStyle(cellStyle);
				myExcelSheet.autoSizeColumn(i);
			}

			for (int rowNo = 1; rowNo <= keys.length; rowNo++) {

				row = myExcelSheet.createRow(rowNo);

				if (row == null) {
					row = myExcelSheet.createRow(rowNo);
				}
				row.createCell(0).setCellValue(keys[rowNo - 1]);

			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creatExcel() {
		try {
			File file = new File(xlPath);
			if (!file.exists()) {
				XSSFWorkbook workbook = new XSSFWorkbook();
				workbook.createSheet(sheet);
				FileOutputStream fos = new FileOutputStream(
						new File(xlPath));
				workbook.write(fos);
				workbook.close();
				write();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		try {
			PubAds pub = new PubAds();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println("Before : " + dtf.format(now));
//			pub.creatExcel();
			pub.readDocumnet();
			extent.HeaderChildNode("DFP not null validation");
//			pub.validateForNotNull();
			LocalDateTime now1 = LocalDateTime.now();
			System.out.println("After : " + dtf.format(now1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cont(String URL) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		for (String url1 : Prerollai) {
			if (URL.contains(url1)) {
				colNumber = 2;
				break;
			}
		}
		for (String url1 : Midrollai) {
			if (URL.contains(url1)) {
				colNumber = 3;
				break;
			}
		}
		for (String url1 : Postrollai) {
			if (URL.contains(url1)) {
				colNumber = 4;
				break;
			}
		}

	}

//	Validate each cell for empty
	public void validateForNotNull() {

		int rowCount = ExcelFunctions.getRowCount(xlPath, sheet);
		for (int i = 1; i < 5; i++) {

			if (getcolumnCount(i)) {
				for (int j = 1; j <= rowCount; j++) {

					String cellValue = ExcelFunctions.getCellValue(xlPath, sheet, j, i);
					if (cellValue.trim().isEmpty()) {
						fillCellColor(j, i);
						String roll = ExcelFunctions.getCellValue(xlPath, sheet, 0, i);
						String emptyValueKey = ExcelFunctions.getCellValue(xlPath, sheet, j, 0);
						System.out.println(roll + " == " + emptyValueKey);
						extent.extentLoggerFail("","Call : "+roll+" Null Value for the Key : "+emptyValueKey);
					}
				}
			} else {
				ExcelFunctions.getCellValue(xlPath, sheet, 0, i);
//				System.out.println(roll);
			}
		}
	}

	public boolean getcolumnCount(int col) {
		int rowCount = ExcelFunctions.getRowCount(xlPath, sheet);
		int Counter = 0;
		for (int j = 1; j <= rowCount; j++) {
			String emptyValueKey = ExcelFunctions.getCellValue(xlPath, sheet, j, col);
			if (emptyValueKey.trim().isEmpty()) {
				Counter++;
			}
		}
		if (Counter == rowCount) {
			return false;
		} else {
			return true;
		}
	}

//	Fill cell with red color for empty cell
	public void fillCellColor(int row, int col) {
		try {
			myExcelBook = new XSSFWorkbook(new FileInputStream(xlPath));
			myExcelSheet = myExcelBook.getSheet(sheet);
			Row Cellrow = myExcelSheet.getRow(row);
			XSSFCellStyle cellStyle = myExcelBook.createCellStyle();
			cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			cellStyle.setFillPattern(FillPatternType.FINE_DOTS);
			if (Cellrow.getCell(col) == null) {
				Cellrow.createCell(col);
			}
			Cell cell1 = Cellrow.getCell(col);
			cell1.setCellStyle(cellStyle);
			FileOutputStream fos = new FileOutputStream(new File(xlPath));
			myExcelBook.write(fos);
			fos.close();
		} catch (Exception e) {
		}
	}

	private final static String DOCTYPE_SEQUENCE = "<doctype-sequence/>";
	private final static Pattern patern = Pattern.compile("(?i)<!DOCTYPE>");

	public static void convertXml(String inFile, String outFile) throws Exception {
		String xmlString = FileUtils.readFileToString(new File(inFile), Charset.forName("UTF-8"));

		// * Remove the doctype sequence if found
		String doctype = "";
		Matcher matcher = patern.matcher(xmlString);
		if (matcher.find()) {
			doctype = matcher.group(0);
			xmlString = xmlString.replace(doctype, DOCTYPE_SEQUENCE);
		}

		// *
//	    Document document = Jsoup.parse(xmlString, "UTF-8", Parser.xmlParser());
//	    FileUtils.writeStringToFile(new File(outFile), ((Element) document).html().replace(DOCTYPE_SEQUENCE, doctype), "UTF-8");           
	}
}
