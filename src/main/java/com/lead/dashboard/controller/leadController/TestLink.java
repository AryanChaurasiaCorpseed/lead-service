package com.lead.dashboard.controller.leadController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.*; 
import java.awt.image.BufferedImage; 
import java.io.ByteArrayInputStream; 
import java.io.ByteArrayOutputStream; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 
public class TestLink {

	public static String UPLOAD_DIR="C:/Users/user/Documents/imageTest/image (1)";

	public static void main(String[] args) throws Exception {
		String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
		String destinationFile = "image.jpg";

		saveImage(imageUrl, destinationFile);
	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		
		byte[] bytes = "testData".getBytes();
		File file = new File(UPLOAD_DIR);

		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(bytes);
		}

        


	}

}
