package Utilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestScreenshotUsingAshot extends TestBrowsers {

	public static void sshot(String methodname, String SavePath) throws IOException {
		Screenshot screenshot = new AShot()
				.shootingStrategy(ShootingStrategies.viewportPasting(Integer.MAX_VALUE)) // Ensures full-page capture
				.shootingStrategy(ShootingStrategies.scaling(2.0f)) // Scales the capture for better results
				.takeScreenshot(driver);

		String directory = SavePath;
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm");
		String currentDate = LocalDateTime.now().format(dtf);
		String filePath = directory + File.separator + methodname + "-" + currentDate + ".jpg";

		ImageIO.write(screenshot.getImage(), "jpg", new File(filePath));
		System.out.println("Screenshot saved: " + filePath);
	}

}
