package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryAnalyzer implements IRetryAnalyzer {

    private static final Logger log =
            LogManager.getLogger(retryAnalyzer.class);

    private int retryCount = 0;
    private static final int maxRetryCount = 1; // retry once

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;

            log.warn(
                "Retrying test: {} | Retry attempt: {}",
                result.getMethod().getMethodName(),
                retryCount
            );

            return true;
        }

        log.error(
            "Test failed after {} retries: {}",
            maxRetryCount,
            result.getMethod().getMethodName()
        );

        return false;
    }
}
