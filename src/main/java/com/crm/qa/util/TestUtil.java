package com.crm.qa.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/*import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;*/
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.crm.qa.base.TestBase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestUtil extends TestBase {
	/*
	 ******************** public common variables
	 */
	public static long PAGE_LOAD_TIMEOUT = 30; 
	public static long IMPLICIT_WAIT = 20;
	public static String TEST_DATA_FILE_PATH = "C:/Users/bbdnet10082/Desktop/NedBank-Automation/FreeCRMTest/"
			+"src/main/java/com/crm/qa/testdata/FreeCRMTestData.xlsx";
	static Workbook book;
	static Sheet sheet;

	
	/*
	 * ***************************public common methods
	 */
	
	public void switchToFrame(){
		driver.switchTo().frame("mainpanel");
		log.info("Switched to mainpanel frame");
	}
	
//********** Data driven approach using Excel POI API*********************
	
	public static Object[][] getTestData(String sheetName){
		FileInputStream TestDataExcel = null;
		
		try{
			TestDataExcel = new FileInputStream(TEST_DATA_FILE_PATH);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		try{
			book = WorkbookFactory.create(TestDataExcel);
		}catch(InvalidFormatException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		//System.out.println(sheet.getLastRowNum()+"------------"+
		//sheet.getRow(0).getLastCellNum());
		for ( int i =0; i < sheet.getLastRowNum(); i++){
			for (int k=0; k < sheet.getRow(0).getLastCellNum(); k++){
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
				//System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
//***********Exception ScreenShots for failure when Testng Test Suite has been run************************	
	
	public static void failureTestScreenShot(String TestName) throws IOException {

		try {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String RunDate =  new SimpleDateFormat("dd_MM_yyyy").format(new Date());
		String filename =  new SimpleDateFormat("dd_MM_yyyy HH-mm-ss").format(new Date());
		FileUtils.copyFile(scrFile, new File(currentDir+"/FailureTestSuiteScreens/"+RunDate+".//" +TestName+"_"+filename+".png"));
		System.out.println(TestName+ ":Test Failed & Screenshot has been taken");
		}catch(Exception e){
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		}

//***********Exception ScreenShots for failure on single test run************************	
	
	public static void failureScreenCaptureOnManualRun() throws IOException {

		try {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String filename =  new SimpleDateFormat("dd_MM_yyyy HH-mm-ss").format(new Date());
		FileUtils.copyFile(scrFile, new File(currentDir+"/FailedTestscreens/"+filename+".png"));
		System.out.println("Screenshot taken for failed tests");
		}catch(Exception e){
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		}
	
//***********Passed ScreenShots when Testng Test Suite has been run and test has passed************************	
	
	public static void passedTestScreenShot(String TestName) throws IOException {

		try {
		Thread.sleep(1000);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String RunDate =  new SimpleDateFormat("dd_MM_yyyy").format(new Date());
		String filename =  new SimpleDateFormat("dd_MM_yyyy HH-mm-ss").format(new Date());
		FileUtils.copyFile(scrFile, new File(currentDir+"/PassedTestSuiteScreens/"+RunDate+".//" +TestName+"_"+filename+".png"));
		System.out.println(TestName+ ":Test Success & Screenshot has been taken");
		}catch(Exception e){
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		}	
	
//************** Scroll screen to the bottom of the web page**********************************
	
	public static void scrollToBottomOfPageScreen(){
		try {
			Thread.sleep(2000);
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//*****************Take screenshot of particular WebElement using AShot screenshot Utility*****************************************	
	
	public static void webElementscreenshot(String TestName) throws IOException{
		WebElement element = driver.findElement(By.xpath(".//*[@id='slide_1']/h1"));
		String currentDir = System.getProperty("user.dir");
		String RunDate =  new SimpleDateFormat("dd_MM_yyyy").format(new Date());
		String filename =  new SimpleDateFormat("dd_MM_yyyy HH-mm-ss").format(new Date());
		Screenshot screenshot = new AShot().takeScreenshot(driver, element);
		ImageIO.write(screenshot.getImage(), ".png", new File(currentDir+"/PassedTestSuiteScreens/"+RunDate+".//" +TestName+"_"+filename+".png"));
		
	}
	
//*****************Take screenshot Passed Tests using AShot screenshot Utility*****************************************
	
	
	public static void takeFullPageScreenshot(String TestName) throws IOException{
		try {
		Thread.sleep(1000);
		String currentDir = System.getProperty("user.dir");
		String RunDate =  new SimpleDateFormat("dd_MM_yyyy").format(new Date());
		String filename =  new SimpleDateFormat("dd_MM_yyyy HH-mm-ss").format(new Date());
		File outFile = new File(currentDir+"/PassedTestSuiteScreens/"+RunDate+".//"+TestName+"_"+filename+".png");
		File parentDir = outFile.getParentFile();
		parentDir.mkdirs();
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
		BufferedImage Image = screenshot.getImage();
		ImageIO.write(Image, "PNG", outFile);
		System.out.println("Passed Test FullScreenshot taken under this path-> "+outFile);
		}
		catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/*public static void takeFullPageScreenshot(String TestName) throws IOException{
		try {
		Thread.sleep(1000);
		String fileSeperator = System.getProperty("file.separator");
		String currentDir = System.getProperty("user.dir");
		String RunDate =  new SimpleDateFormat("dd_MM_yyyy").format(new Date());
		String filename =  new SimpleDateFormat("dd_MM_yyyy HH-mm-ss").format(new Date());
		Path path = Paths.get(currentDir+"/PassedTestSuiteScreens/"+RunDate);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}else{	
			System.out.println("Today's Screenshot Folder already Exists!");
		}
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
		BufferedImage Image = screenshot.getImage();
		System.out.println("Passed Test FullScreenshot taken under this path ->"+path);
		ImageIO.write(Image, "PNG", new File(path +fileSeperator+TestName+ "_" +filename+".png"));
		}
		catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	*/

	
	

	
	
	
	
	
	
	
	
	
}	
	
	
	

