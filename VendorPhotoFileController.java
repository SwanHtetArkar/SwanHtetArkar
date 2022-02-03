package application;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*; 
import javax.imageio.ImageIO;

public class VendorPhotoFileController {
	
	public static boolean createPhoto(String originalFileName, String photoName) {
		try{
		    File imagefile = new File(originalFileName);
		    BufferedImage image = ImageIO.read(imagefile);
		    if (ImageIO.write(image, "png",new File(photoName+".jpg"))) {
				return true;
			}
		    return false;
		}catch (Exception e) {
			return false;
		}
	}
}
