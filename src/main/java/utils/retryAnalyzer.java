package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 1; // retry twice

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println(
                "Retrying test: " + result.getMethod().getMethodName() +
                " | Retry attempt: " + retryCount
            );
            return true;
        }
        return false;
    }

}



