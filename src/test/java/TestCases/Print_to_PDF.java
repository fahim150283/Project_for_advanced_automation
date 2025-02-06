package TestCases;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.print.PrintOptions;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class Print_to_PDF extends TestBrowsers {

    @Test
    public static void Print_to_PDF() throws Exception {

        driver.get("https://selenium.dev/");
        Thread.sleep(2000);


        // Ensure the PDFs directory exists
        Path pdfFolderPath = Paths.get("./PDFs");
        if (!Files.exists(pdfFolderPath)) {
            Files.createDirectories(pdfFolderPath);
        }

        // Save the PDF file
        Path pdfFilePath = pdfFolderPath.resolve("selenium.pdf");
        Pdf pdf = ((PrintsPage) driver).print(new PrintOptions());
        Files.write(pdfFilePath, OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
    }
}
