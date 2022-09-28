package com.zee5.DFP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.extent.ExtentReporter;

public class ParseCharlesLogs {

	static int colNumber = 0;
	static ExtentReporter extent = new ExtentReporter();

	public static ArrayList<String> VMAXAdResponseCampaignName = new ArrayList<String>();
	public static ArrayList<String> VMAXAdResponseCampaignID = new ArrayList<String>();
	public static ArrayList<String> VMAXAdResponseAdName = new ArrayList<String>();
	public static ArrayList<String> VMAXAdResponseAdTime = new ArrayList<String>();

	public static int VMAXAdNameIndex = 0;

	@SuppressWarnings("unused")
	public static void readDocumnet()
			throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		ArrayList<String> AllCalls = new ArrayList<String>();
		File file = new File(System.getProperty("user.dir") + "//" + CharlesConfigure.charlesName);
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
		validateResponse(doc);
	}

	@SuppressWarnings("unused")
	public static void validateResponse(Document doc) {
		extent.HeaderChildNode("Validation API Response");
		ArrayList<String> eachOne = new ArrayList<String>();
		NodeList node = doc.getElementsByTagName("transaction");
		System.out.println(node.getLength());
		System.out.println(node.toString());
		for (int i = 0; i < node.getLength(); i++) {
			int j = 0;
			if (node.item(i).getTextContent().trim().contains("stagingb2bapi.zee5.com")) {
				extent.extentLoggerPass("", "stagingb2bapi.zee5.com API is triggered");
				System.out.println("stagingb2bapi.zee5.com API is triggered");
				break;
			} else {
				j = i;
			}
			if (j == node.getLength() - 1) {
				extent.extentLoggerFail("", "stagingb2bapi.zee5.com API is not triggered");
				System.out.println("stagingb2bapi.zee5.com API is not triggered");
			}
		}
		for (int i = 0; i < node.getLength(); i++) {
			int j = 0;
			if (node.item(i).getTextContent().trim().contains("config.php")) {
				extent.extentLoggerPass("", "config.php API is triggered");
				System.out.println("config.php API is triggered");
				break;
			} else {
				j = i;
			}
			if (j == node.getLength() - 1) {
				extent.extentLoggerFail("", "config.php API is not triggered");
				System.out.println("config.php API is not triggered");
			}
		}
		for (int i = 0; i < node.getLength(); i++) {
			int j = 0;
			if (node.item(i).getTextContent().trim().contains("pubads.g.doubleclick.net")) {
				extent.extentLoggerPass("", "pubads.g.doubleclick.net API is triggered");
				System.out.println("pubads.g.doubleclick.net API is triggered");

				if (node.item(i).getTextContent().trim().contains("/gampad/ads?iu")) {
					System.out.println("/gampad/ads?iu is Present");
				} else {

				}

				break;
			} else {
				j = i;
			}
			if (j == node.getLength() - 1) {
				extent.extentLoggerFail("", "pubads.g.doubleclick.net API is not triggered");
				System.out.println("pubads.g.doubleclick.net API is not triggered");
			}
		}
		for (int i = 0; i < node.getLength(); i++) {
			int j = 0;
			if (node.item(i).getTextContent().trim().contains("px.moatads.com")) {
				extent.extentLoggerPass("", "px.moatads.com API is triggered");
				System.out.println("px.moatads.com API is triggered");
				break;
			} else {
				j = i;
			}
			if (j == node.getLength() - 1) {
				extent.extentLoggerFail("", "px.moatads.com API is not triggered");
				System.out.println("px.moatads.com API is not triggered");
			}
		}
	}

	@SuppressWarnings({ "resource", "unused", "rawtypes" })
	public static void oneTag(Document doc) throws IOException {
		ArrayList<String> eachOne = new ArrayList<String>();
		String array[];
		NodeList node = doc.getElementsByTagName("transaction");
		System.out.println(node.getLength());
		for (int i = 0; i < node.getLength(); i++) {
			// System.out.println("--------------------------------------------------------------");
			eachOne.add(node.item(i).getTextContent().trim());
		}
		String event = "zee5_applicaster_app_android_moat_enabled_preroll";
		for (int j = 0; j < eachOne.size(); j++) {
			if (eachOne.get(j).contains(event)) {
				System.out.println("========================================================");
				System.out.println(event + " is Present");
				System.out.println("========================================================");
				String trimString = eachOne.get(j);
				String[] delimiter = trimString.split("\n")[10].split("&");
				System.out.println(delimiter.length);
				for (int i = 0; i < delimiter.length; i++) {
					System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
					String Key = delimiter[i].split("=")[0];
					String Value = delimiter[i].split("=")[1];
					String KeyData = Key;
					String ValueData = Value.replace("%3D", "=").replace("%26", "&").replace("%2B", "+")
							.replace("%3A", ":").replace("%2F", "/");
					int preRoll = 1;
					int midRoll = 1;
					int postRoll = 1;
					InputStream ExcelFileToRead = new FileInputStream(
							System.getProperty("user.dir") + "\\DFPExcelDump" + "\\" + "DFP0091" + ".xlsx");
					XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
					XSSFSheet sheet = wb.getSheetAt(0);
					XSSFRow row;
					XSSFCell cell;
					Iterator rows = sheet.rowIterator();
					int starRow = sheet.getFirstRowNum();
					System.out.println("Row start : " + starRow);
					int endRow = sheet.getLastRowNum();
					System.out.println("Row end : " + endRow);
					String keyFromExcel = "";
					for (int k = 0; k < endRow; k++) {
						XSSFCell KeyName = sheet.getRow(k + 1).getCell(0);
						String KeyName1 = KeyName.toString();
						int rownumberOfEvent = KeyName.getRow().getRowNum();

						if (KeyName1.equalsIgnoreCase(KeyData)) {
							System.out.println("M A T C H E D");
							writetoExcel(rownumberOfEvent, KeyData, ValueData);
						} else {
							System.out.println("* * * * * * * ");
						}
					}
				}
			} else {
				System.out.println("========================================================");
				System.out.println(event + " is not Present");
			}
		}
	}

	public static void writetoExcel(int i, String key, String value) {
		// System.out.println("WriteToExcel");

		String xlpath = System.getProperty("user.dir") + "\\DFPExcelDump" + "\\" + "DFP0091" + ".xlsx";
		String Previousdata = "";
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
			FileOutputStream output = new FileOutputStream(xlpath);
			XSSFSheet myExcelSheet = myExcelBook.getSheet("Sheet1");
			if (value.contains("preroll")) {
				colNumber = 2;
			} else if (value.contains("postroll")) {
				colNumber = 4;
			} else if (value.contains("midroll")) {
				colNumber = 3;
			}

			int rc = myExcelSheet.getLastRowNum();
			for (int noRow = 0; noRow < rc; noRow++) {
				if (myExcelSheet.getRow(noRow + 1).getCell(0).toString().equals(key)) {

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
					if (cell == null) {
						cell = row.createCell(colNumber);
					}
					cell.setCellValue(Previousdata + "\n" + value);
					System.out.println(Previousdata + "\n" + value);
					break;
				}
			}
			myExcelBook.write(output);
			myExcelBook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// VMAX

	public static void main(String args[]) throws Exception {
		System.out.println("VMAX");
		readVMAXDocumnet();
	}

	@SuppressWarnings("unused")
	public static void readVMAXDocumnet()
			throws ParserConfigurationException, SAXException, IOException, InterruptedException, ParseException {
		ArrayList<String> AllCalls = new ArrayList<String>();

		File file = new File(System.getProperty("user.dir") + "//" + CharlesConfigure.charlesName);

		// File file = new File("C:\\Users\\IGS
		// Admin\\Desktop\\Vmax\\VMAXContentLog_260221150818.chlsx");

		// File file = new File(System.getProperty("user.dir") +"//"+
		// "VMAXContentLog_2423211808441.chlsx");

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
		validateVMAXResponse(doc);
	}

	@SuppressWarnings("unused")
	public static void validateVMAXResponse(Document doc) throws ParseException {
		extent.HeaderChildNode("Validation VMAX Response");

		ArrayList<String> eachOne = new ArrayList<String>();
		NodeList node = doc.getElementsByTagName("transaction");
//		System.out.println(node.getLength());
//		System.out.println(node.toString());

		for (int i = 0; i < node.getLength(); i++) {
			if (node.item(i).getTextContent().contains("/delivery/adapi.php")) {
				eachOne.add(node.item(i).getTextContent().trim());
			}
		}

		String event = "X-VSERV-BODY";
		for (int j = 0; j < eachOne.size(); j++) {
			if (eachOne.get(j).contains(event)) {
				// System.out.println("========================================================");
				// System.out.println(event + " is Present");
				// System.out.println("========================================================");
				String trimString = eachOne.get(j);
				// System.out.println(trimString);

				// System.out.println(trimString.split("\n")[90]);
				// System.out.println(trimString.split("\n")[91]);
				String resp = null;
				for (int i = 1; i <= 150; i++) {
					String data = trimString.split("\n")[i];
					if (data.equalsIgnoreCase(event)) {
						int k = i;
						resp = trimString.split("\n")[k + 1];
						break;
					}
				}
				
				try {
					String resp1 = JSONParseJSONObjectResponse(resp, "adInfo").toString();
					System.out.println(resp1);
					// CAMPAIGN
					String campaignData = JSONParseJSONObjectResponse(resp1, "campaign").toString();
					String campaignName = JSONParseJSONObjectResponse(campaignData, "name").toString();
					String campaignID = JSONParseJSONObjectResponse(campaignData, "id").toString();

					// Ad
					String AdData = JSONParseJSONObjectResponse(resp1, "ad").toString();
					String AdName = JSONParseJSONObjectResponse(AdData, "name").toString();
					String AdTime = JSONParseJSONObjectResponse(AdData, "playback-duration").toString();

					extent.extentLogger("", "campaignName : " + campaignName);
					VMAXAdResponseCampaignName.add(campaignName);
					extent.extentLogger("", "campaignID : " + campaignID);
					VMAXAdResponseCampaignID.add(campaignID);
					extent.extentLogger("", "AdName : " + AdName);
					VMAXAdResponseAdName.add(AdName);
					extent.extentLogger("", "AdTime : " + AdTime);
					VMAXAdResponseAdTime.add(AdTime);
					extent.extentLogger("",
							"-------------------------------------------------------------------------");
				} catch (Exception e) {

				}

				// break;

			}
		}

		System.out.println(VMAXAdResponseCampaignName);
		System.out.println(VMAXAdResponseCampaignID);
		System.out.println(VMAXAdResponseAdName);
		System.out.println(VMAXAdResponseAdTime);

//		ParseCharlesLogs.VMAXValidation("Baarish","32","Stage Companion Preroll","77237");
//		ParseCharlesLogs.VMAXValidation("SOS","30","Stage Companion Bumper","77238");		
//		ParseCharlesLogs.VMAXValidation("Maafia","15","Stage Video MidrollA","77240");
//		ParseCharlesLogs.VMAXValidation("Yaara","15","Stage Companion MidrollB","77243");

	}

	public static void VMAXValidation(String AdName, String AdTime, String CampaignName, String CampaignID) {
		boolean flag = false;
		System.out.println(VMAXAdResponseAdName.size());

		for (int i = 0; i < VMAXAdResponseAdName.size(); i++) {
			if (VMAXAdResponseAdName.get(i).contains(AdName)) {
				System.out.println(AdName + " is present");
				VMAXAdNameIndex = i;
				flag = true;
				break;
			} else {
				if (i == VMAXAdResponseAdName.size() - 1) {
					System.out.println("Video FallBack Has Triggered in place of Ad: " + AdName);
					flag = false;
				}
			}
		}

		// System.out.println(VMAXAdNameIndex);

		extent.extentLogger("", "----------------------------------------------------------------------------------");

		if (flag == true) {
			if (VMAXAdResponseCampaignName.get(VMAXAdNameIndex).contains(CampaignName)) {
				System.out.println(CampaignName + " is Present");
				extent.extentLoggerPass("", CampaignName + " is Present");
			} else {
				System.out.println(CampaignName + " is not Present");
				extent.extentLoggerFail("", CampaignName + " is not Present");
			}

			if (VMAXAdResponseCampaignID.get(VMAXAdNameIndex).contains(CampaignID)) {
				System.out.println(CampaignID + " is Present");
				extent.extentLoggerPass("", CampaignID + " is Present");
			} else {
				System.out.println(CampaignID + " is not Present");
				extent.extentLoggerFail("", CampaignID + " is not Present");
			}

			if (VMAXAdResponseAdName.get(VMAXAdNameIndex).contains(AdName)) {
				System.out.println(AdName + " is Present");
				extent.extentLoggerPass("", AdName + " is Present");
			} else {
				System.out.println(AdName + " is not Present");
				extent.extentLoggerFail("", AdName + " is not Present");
			}

			if (VMAXAdResponseAdTime.get(VMAXAdNameIndex).contains(AdTime)) {
				System.out.println(AdTime + " is Present");
				extent.extentLoggerPass("", AdTime + " is Present");
			} else {
				System.out.println(AdTime + " is not Present");
				extent.extentLoggerFail("", AdTime + " is not Present");
			}
		}

		if (flag == false) {
			if (VMAXAdResponseCampaignName.contains("Video Fallback")) {
				System.out.println("Video Fallback is Present");
				extent.extentLoggerPass("", "Video Fallback is Present");
			} else {
				System.out.println("Video Fallback is not Present");
				extent.extentLoggerFail("", "Video Fallback is not Present");
			}

			if (VMAXAdResponseCampaignID.contains("77239")) {
				System.out.println("77239 is Present");
				extent.extentLoggerPass("", "77239 is Present");
			} else {
				System.out.println("77239 is not Present");
				extent.extentLoggerFail("", "77239 is not Present");
			}

			if (VMAXAdResponseAdName.contains("Mafia_Teaser_14secs")) {
				System.out.println("Mafia_Teaser is Present");
				extent.extentLoggerPass("", "Mafia_Teaser is Present");
			} else {
				System.out.println("Mafia_Teaser is not Present");
				extent.extentLoggerFail("", "Mafia_Teaser is not Present");
			}

			if (VMAXAdResponseAdTime.contains("15")) {
				System.out.println("15 is Present");
				extent.extentLoggerPass("", "15 is Present");
			} else {
				System.out.println("15 is not Present");
				extent.extentLoggerFail("", "15 is not Present");
			}

		}

	}

	public static Object JSONParseJSONObjectResponse(String sourceData, String responseData) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(sourceData);
		// System.out.println(json.get(responseData));
		return json.get(responseData);

	}

}
