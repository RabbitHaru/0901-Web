package org.zerock.springinterface.service;

import org.zerock.springinterface.domain.dto.BlogDTO;
import java.util.List;

public interface BlogService {

    // 모든 블로그 조회
    List<BlogDTO> getAllBlogs();

    // ID로 블로그 조회
    BlogDTO getBlogById(Integer id);

    // 블로그 작성
    boolean insertBlog(BlogDTO blogDTO);

    // 블로그 수정
    boolean updateBlog(BlogDTO blogDTO);

    // 블로그 삭제
    boolean deleteBlog(Integer id);

    // 블로그 총 개수
    int getTotalBlogCount();
}