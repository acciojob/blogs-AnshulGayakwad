package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog(title, content, new Date());
        User user = userRepository1.findById(userId).get();
        blog.setUser(user);

        List<Blog> currentBlogs = user.getBlogList();
        currentBlogs.add(blog);
        user.setBlogList(currentBlogs);

        //blogRepository1.save(blog);
        userRepository1.save(user);
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        //Blog blog = blogRepository1.findById(blogId).get();
//        User user = blog.getUser();
//        List<Blog> blogsList = user.getBlogList();
//        blogsList.remove(blog);
//        user.setBlogList(blogsList);
//
//        List<Image> imagesList = blog.getImageList();
//
//        imagesList.clear();
//        blog.setImageList(imagesList);
//        blogRepository1.save(blog);
//
        blogRepository1.deleteById(blogId);
//        userRepository1.save(user);

    }
}
