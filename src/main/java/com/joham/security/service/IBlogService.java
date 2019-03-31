package com.joham.security.service;


import com.joham.security.entity.Blog;

import java.util.List;

/**
 * @author joham
 */
public interface IBlogService {

    List<Blog> getBlogs();

    void deleteBlog(long id);
}
