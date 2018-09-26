package com.crm.qa.util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.qa.base.TestBase;

public class ITestListenerTest extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
			try {
				TestUtil.takeFullPageScreenshot(result.getName());
				//TestUtil.passedTestScreenShot(result.getName());
				log.info("Passed Test Screenshots Taken Successfully");
			} catch (IOException e) {
				e.printStackTrace();
				log.error("Unable to take Screenshots");
			}
		} 

	public void onTestFailure(ITestResult result) {
		try {
			TestUtil.failureTestScreenShot(result.getName());
			log.info("Failed Test Screenshots Taken Successfully");
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Unable to take Screenshots");
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
