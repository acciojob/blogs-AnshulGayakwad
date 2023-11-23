package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        //Blog blog = blogRepository2.findById(blogId).orElse(null);
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description, dimensions);
        image.setBlog(blog);

        List<Image> imagesList = blog.getImageList();
        imagesList.add(image);
        blog.setImageList(imagesList);

        blogRepository2.save(blog);
       // imageRepository2.save(image);
        return  image;
    }

    public void deleteImage(Integer id){
//        Image image = imageRepository2.findById(id).get();
//        if (image != null) {
//            Blog blog = image.getBlog();
//            List<Image> imagesList = blog.getImageList();
//            imagesList.remove(image);
//            blog.setImageList(imagesList);

            imageRepository2.deleteById(id);
//            blogRepository2.save(blog);
            //blogRepository2.deleteById(id);
        //}
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //Image image = imageRepository2.findById(id).orElse(null);
        /*
        Image image = imageRepository2.findById(id).get();
        String screenDimension[] = screenDimensions.split("X");
        String imgDimension[] = image.getDimensions().split("X");

        int integerScreen = Integer.parseInt(screenDimension[0])*Integer.parseInt(screenDimension[1]);
        int integerImage = Integer.parseInt(imgDimension[0])*Integer.parseInt(imgDimension[1]);

        return integerScreen/integerImage;
        */

        //4X4 = 4/2*4/2 == 2*2== 4 images
        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        //image dimension is in String format ex: 2X2
        //we have to convert it into integer like 2*2=4
        //below is the process to calculate image dimension as width and height

        int indexOfX = imageDimensions.indexOf('X');

        String x = imageDimensions.substring(0,indexOfX);
        String y = imageDimensions.substring(indexOfX+1);

        int imageWidth = Integer.parseInt(x);
        int imageHeight= Integer.parseInt(y);


        //Like above, Similarly find screen dimension in integer format

        int screenIndexOfX = screenDimensions.indexOf('X');

        String screenX = screenDimensions.substring(0,screenIndexOfX);
        String screenY = screenDimensions.substring(screenIndexOfX+1);

        int screenWidth = Integer.parseInt(screenX);
        int screenHeight = Integer.parseInt(screenY);

        int count = (screenWidth/imageWidth) * (screenHeight/imageHeight);

        return count;
    }
}
