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

        imageRepository2.save(image);
        return  image;
    }

    public void deleteImage(Integer id){
        blogRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //Image image = imageRepository2.findById(id).orElse(null);
        Image image = imageRepository2.findById(id).get();
        String screenDimension[] = screenDimensions.split("X");
        String imgDimension[] = image.getDimensions().split("X");

        int integerScreen = Integer.parseInt(screenDimension[0])*Integer.parseInt(screenDimension[1]);
        int integerImage = Integer.parseInt(imgDimension[0])*Integer.parseInt(imgDimension[1]);

        return integerScreen/integerImage;
    }
}
