package com.zee5.DFP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.*;


public class CharlesConfigure {

	static InetAddress localhost;
	static String PortNumber = "8888";
	public static String charlesName ;
	public static boolean charles = false;

	public static void main(String[] args) throws IOException, InterruptedException {
//		openCharles();
//		saveCharles("ChlsXMLFile");
//		PubAds.main(null);
//		System.out.println(System.getProperty("user.dir") + "\\DifferenceBtwTemplateAndUIPlayedContent.chlsx");
//		-config C:\\Users\\IGS0026\\Documents\\Charles\\CharlesSettings.xml
		
	        zipFile("C:\\Users\\IGS0026\\Downloads\\jenkins.msi");
	}

	public static void openCharles() throws IOException, InterruptedException {
		try {
			System.out.println("Open Charles");
			Runtime.getRuntime().exec("Charles.exe");
			Thread.sleep(20000);
			localhost = InetAddress.getLocalHost();
			String ipAddress = localhost.getHostAddress().trim();
			System.out.println(ipAddress);
			Thread.sleep(10000);
			Runtime.getRuntime().exec(
					"curl -v -x http://" + ipAddress + ":" + PortNumber + " http://control.charles/session/clear");
			Thread.sleep(2000);
			Runtime.getRuntime().exec(
					"curl -v -x http://" + ipAddress + ":" + PortNumber + " http://control.charles/recording/start");
			charles = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveCharles(String fileName) {
		try {
			DateFormat date = new SimpleDateFormat("ddmmyyHHMMss");
			Date date2 = new Date();
			String date1 = date.format(date2);
			charlesName = fileName + date1 + ".xml";
			String charleslogsName = fileName + date1 + ".chls";
			String filePathxml = "./" + charlesName;
			String filePathlogs = "./" + charleslogsName;
			String ipAddress = localhost.getHostAddress().trim();
			System.out.println(ipAddress);
			Runtime.getRuntime().exec(
					"curl -v -x http://" + ipAddress + ":" + PortNumber + " http://control.charles/recording/stop");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("curl -o " + filePathxml + " -x http://" + ipAddress + ":" + PortNumber
					+ " http://control.charles/session/export-xml");
			Thread.sleep(10000);
			Runtime.getRuntime().exec("curl -o " + filePathlogs + " -x http://" + ipAddress + ":" + PortNumber
					+ " http://control.charles/session/download");
			Thread.sleep(10000);
			Runtime.getRuntime().exec("taskkill /F /IM Charles.exe");
		} catch (Exception e) {

		}
	}
	
	
	public static void clearCharlesLog() throws IOException, InterruptedException{
		
		String ipAddress = localhost.getHostAddress().trim();
		System.out.println(ipAddress);
		
		Thread.sleep(2000);
		Runtime.getRuntime().exec(
				"curl -v -x http://" + ipAddress + ":" + PortNumber + " http://control.charles/session/clear");
		Thread.sleep(2000);
	}	
	

	 private static void zipFile(String filePath) {
	        try {
	            File file = new File(filePath);
	            String zipFileName = file.getName().concat(".zip");
	 
	            FileOutputStream fos = new FileOutputStream(zipFileName);
	            ZipOutputStream zos = new ZipOutputStream(fos);
	 
	            zos.putNextEntry(new ZipEntry(file.getName()));
	 
	            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
	            zos.write(bytes, 0, bytes.length);
	            zos.closeEntry();
	            zos.close();
	 
	        } catch (FileNotFoundException ex) {
	            System.err.format("The file %s does not exist", filePath);
	        } catch (IOException ex) {
	            System.err.println("I/O error: " + ex);
	        }
	    }

}