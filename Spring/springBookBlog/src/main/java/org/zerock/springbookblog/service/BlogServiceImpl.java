package org.zerock.springbookblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.springbookblog.domain.BlogVO;
import org.zerock.springbookblog.domain.dto.BlogDTO;
import org.zerock.springbookblog.mappers.BlogMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;


    // @Autowired

    @Override
    public List<BlogDTO> getAllBlogs() {
        List<BlogVO> blogList = blogMapper.getAllBlogs();
        return blogList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO getBlogById(Integer id) {
        BlogVO blog = blogMapper.getBlogById(id);
        if (blog == null) return null;

        BlogDTO blogDTO = convertToDTO(blog);
        return blogDTO;
    }

    @Override
    public boolean insertBlog(BlogDTO blogDTO) {
        BlogVO blog = convertToVO(blogDTO);
        int result = blogMapper.insertBlog(blog);
        return result > 0;
    }

    @Override
    public boolean updateBlog(BlogDTO blogDTO) {
        BlogVO blog = convertToVO(blogDTO);
        int result = blogMapper.updateBlog(blog);
        return result > 0;
    }

    @Override
    public boolean deleteBlog(Integer id) {
        int result = blogMapper.deleteBlog(id);
        return result > 0;
    }

    @Override
    public int getTotalBlogCount() {
        return blogMapper.getTotalBlogCount();
    }


    // VO → DTO 변환
    private BlogDTO convertToDTO(BlogVO blogVO) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blogVO.getId());
        dto.setTitle(blogVO.getTitle());
        dto.setContent(blogVO.getContent());
        dto.setWriter(blogVO.getWriter());
        dto.setUsername(blogVO.getUsername());
        dto.setImg(blogVO.getImg());
        dto.setBookTitle(blogVO.getBookTitle());
        dto.setBookAuthor(blogVO.getBookAuthor());
        dto.setBookPublisher(blogVO.getBookPublisher());
        dto.setBookRating(blogVO.getBookRating());
        dto.setCreatedAt(blogVO.getCreatedAt());
        return dto;
    }

    // DTO → VO 변환
    private BlogVO convertToVO(BlogDTO blogDTO) {
        BlogVO vo = new BlogVO();
        vo.setId(blogDTO.getId());
        vo.setTitle(blogDTO.getTitle());
        vo.setContent(blogDTO.getContent());
        vo.setWriter(blogDTO.getWriter());
        vo.setImg(blogDTO.getImg());
        vo.setBookTitle(blogDTO.getBookTitle());
        vo.setBookAuthor(blogDTO.getBookAuthor());
        vo.setBookPublisher(blogDTO.getBookPublisher());
        vo.setBookRating(blogDTO.getBookRating());
        return vo;
    }
}