package org.zerock.springbookblog.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.springbookblog.domain.BlogVO;

import java.util.List;

@Mapper
public interface BlogMapper {

    // 모든 블로그 조회
    List<BlogVO> getAllBlogs();

    // ID로 블로그 조회
    BlogVO getBlogById(Integer id);

    // 블로그 작성
    int insertBlog(BlogVO blog);

    // 블로그 수정
    int updateBlog(BlogVO blog);

    // 블로그 삭제
    int deleteBlog(Integer id);

    // 블로그 총 개수
    int getTotalBlogCount();
}