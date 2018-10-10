package com.crm.qa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.crm.qa.base.TestBase;

public class RetryAnalyzer extends TestBase implements IRetryAnalyzer {
	
	int counter = 0;
	int RetryLimit = 3;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(counter< RetryLimit){
			counter++;
			return true;
		}
		return false;
	}

}
