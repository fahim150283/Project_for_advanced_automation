package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.model.Headers;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.Response;
import org.testng.annotations.Test;

import java.util.Optional;

public class TestRequestAndResposneHeaders extends TestBrowsers {

    @Test
    public void testRequestAndResponseHeaders() {
        fluentwait.until(d -> {
            try {
                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

                devTools.addListener(Network.requestWillBeSent(), request -> {
                    String initiatorType = String.valueOf(request.getInitiator().getType()); // Get request type
                    Headers header = request.getRequest().getHeaders();
                    if ("xmlhttprequest".equalsIgnoreCase(initiatorType) || "fetch".equalsIgnoreCase(initiatorType)) {
                        System.out.println("************************************************************************************************************************");
                        System.out.println("XHR/Fetch Request URL: " + request.getRequest().getUrl());
                        System.out.println("Request Method: " + request.getRequest().getMethod());
                        System.out.println("Request Headers: ");
                        header.forEach((key, value) -> {
                            System.out.println("  " + key + " = " + value);
                        });
                    }
                });

                // Capture Response Headers & General Response
                devTools.addListener(Network.responseReceived(), response -> {
                    Response res = response.getResponse();

                    System.out.println("************************************************************************************************************************");
                    System.out.println("URL: " + res.getUrl()); // Print URL
                    System.out.println("Status: " + res.getStatus() + " " + res.getStatusText()); // Print status code and text
                    System.out.println("Request Method: " + res.getHeadersText() + " " + res.getStatusText()); // Print status code and text

                    // Print Response Headers
                    Headers header = res.getHeaders();
                    String initiatorType = String.valueOf(response.getResponse().getProtocol()); // Identify Fetch/XHR response

                    if ("h2".equalsIgnoreCase(initiatorType)) { // HTTP/2 responses often used for Fetch/XHR
                        System.out.println("XHR/Fetch Response URL: " + response.getResponse().getUrl());
                        System.out.println("Status Code: " + response.getResponse().getStatus());
                        System.out.println("Response Headers: ");
                        header.forEach((key, value) -> System.out.println("  " + key + " = " + value));
                    }
                });

                driver.get("https://demoqa.com/books");
                Thread.sleep(3000);

            } catch (NoAlertPresentException | InterruptedException e) {
            }
            return true;
        });
    }
}
