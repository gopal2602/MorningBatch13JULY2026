package driver;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int Max_Retry_Count = 2;


    @Override
    public boolean retry(ITestResult iTestResult) {
        return retryCount++ < Max_Retry_Count;
    }
}
