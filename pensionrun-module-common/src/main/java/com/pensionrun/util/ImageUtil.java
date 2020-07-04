package com.pensionrun.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

import net.coobird.thumbnailator.Thumbnails;

@Component
public class ImageUtil {
	

@Autowired
    private AmazonS3Client amazonS3Client;
    private static float QUALITY=0.9f;
    private static int width = 1980;
    private static int height= 1080;

    public List<String> uploadImage(String bucket, String folder, MultipartFile[] files, String type) {
        // TODO Auto-generated method stub
        List<String> imageList = new ArrayList<String>();
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDT = dayTime.format(new Date(time));
        
                try {
                    if (files != null && files.length != 0) {
                        for (int i = 0; i < files.length; i++) {
                            String extension = getFileExtension(files[i].getOriginalFilename());
                            String url = folder + "/" + strDT + i + "." + extension;
                            String thumnailUrl = folder + "/" + strDT + i + "_thumnail." + extension;
                            amazonS3Client.putObject(
                                    new PutObjectRequest(bucket, url,imageResize(files[i].getInputStream()), new ObjectMetadata()));

                            imageList.add(strDT + i + "." + extension);
                        }
                    }
                } catch (Exception e) {
                    for (String file : imageList) {
                        amazonS3Client.deleteObject(bucket, folder + "/" + file);
                    }
                    imageList = null;
                }
        return imageList;
    }

	public void deleteImage(String bucket, String key) {
		amazonS3Client.deleteObject(bucket, key);
	}

	public void moveImagerFolder(String bucket, String origin, String target) {
		ObjectListing objectListing = amazonS3Client
				.listObjects(new ListObjectsRequest().withBucketName(bucket).withPrefix(origin + "/"));
		for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {

			amazonS3Client.copyObject(bucket, objectSummary.getKey(), bucket,
					objectSummary.getKey().replace(origin, target));

			amazonS3Client.deleteObject(bucket, objectSummary.getKey());

		}
		amazonS3Client.deleteObject(bucket, origin + "/");
	}

	private InputStream imageResize(InputStream file) throws Exception{
        byte[] bytes = IOUtils.toByteArray(file);
        if(bytes.length>600000){
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(bytes));
            Thumbnails.of(originalImage).size(width, height).outputFormat("jpeg").toOutputStream(os);
            return new ByteArrayInputStream(os.toByteArray());
        }else{
            return new ByteArrayInputStream(bytes);
        }
    }
//	private InputStream imageResize(MultipartFile file, String type) throws Exception {
//		int width = 360;
//		int height = 360;
//		if (type.equals("pension")) {
//			 width=360;
//			 height=360;
//		} else if (type.equals("room")) {
//			 width=360;
//			 height=360;
//		} else if (type.equals("review")) {
//			 width=360;
//			 height=360;
//		} else {
//			width = 360;
//			height = 360;
//		}
//		BufferedImage originalImage = ImageIO.read(file.getInputStream());
//
//		int imgwidth = Math.min(originalImage.getHeight(), originalImage.getWidth());
//		int imgheight = imgwidth;
//
//		// BufferedImage scaledImage = Scalr.crop(originalImage,
//		// (originalImage.getWidth() - imgwidth)/2, (originalImage.getHeight() -
//		// imgheight)/2, imgwidth, imgheight, null);
//
//		BufferedImage resizedImage = Scalr.resize(originalImage, width, height, null);
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		ImageIO.write(resizedImage, "gif", os);
//		return new ByteArrayInputStream(os.toByteArray());
//	}

	private String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		String extension = "jpg";
		if (index != -1) {
			extension = fileName.substring(index + 1);
		}
		return extension;
	}
}
