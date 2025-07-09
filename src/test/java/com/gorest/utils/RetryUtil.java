package com.gorest.utils;

import io.restassured.response.Response;

import java.util.function.Supplier;

public class RetryUtil {

    /**
     * Retries the API request if it fails, up to the maxAttempts.
     *
     * @param requestSupplier The lambda that sends the request (e.g., () -> given().get(...))
     * @param maxAttempts     Max retry attempts
     * @param delayMillis     Wait time between retries (in milliseconds)
     * @return Response object from the final attempt (success or failure)
     */
    public static Response retryRequest(Supplier<Response> requestSupplier, int maxAttempts, long delayMillis) {
        int attempt = 0;
        Response response = null;

        while (attempt < maxAttempts) {
            attempt++;
            System.out.println("⚙️ Attempt " + attempt + " of " + maxAttempts);

            try {
                response = requestSupplier.get();

                if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                    System.out.println("✅ Success on attempt " + attempt);
                    return response;
                } else {
                    System.out.println("❌ Attempt " + attempt + " failed with status: " + response.getStatusCode());
                }

                Thread.sleep(delayMillis);

            } catch (Exception e) {
                System.out.println("❗ Exception on attempt " + attempt + ": " + e.getMessage());
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ignored) {}
            }
        }

        System.out.println("🚫 Max retry attempts reached. Returning last response.");
        return response;
    }
}
