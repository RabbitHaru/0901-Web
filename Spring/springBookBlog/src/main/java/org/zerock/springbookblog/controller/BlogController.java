package org.zerock.springbookblog.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springbookblog.domain.dto.BlogDTO;
import org.zerock.springbookblog.service.BlogService;

import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
@Log4j2
public class BlogController {

    @Autowired
    private BlogService blogService;

    // 1. 블로그 목록
    @GetMapping("/list.do")
    public String list(Model model) {
        List<BlogDTO> blogList = blogService.getAllBlogs();
        model.addAttribute("blogList", blogList);
        return "blog/list";
    }

    // 2. 블로그 상세보기
    @GetMapping("/view.do")
    public String view(@RequestParam("id") int id, Model model,
                       RedirectAttributes redirectAttributes) {
        BlogDTO blog = blogService.getBlogById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("error", "not_found");
            return "redirect:/blog/list.do";
        }
        model.addAttribute("blog", blog);
        return "blog/view";
    }

    // 3. 블로그 작성 폼
    @GetMapping("/write.do")
    public String writeForm(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("username") == null) {
            redirectAttributes.addFlashAttribute("error", "login_required");
            return "redirect:/user/login.do";
        }
        return "blog/write";
    }

    // 4. 블로그 작성 처리
    @PostMapping("/write.do")
    public String write(
            @RequestParam("bookTitle") String bookTitle,
            @RequestParam("bookAuthor") String bookAuthor,
            @RequestParam("bookPublisher") String bookPublisher,
            @RequestParam("bookRating") String bookRating,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "img", required = false) MultipartFile imgFile,
            HttpSession session,
            RedirectAttributes redirectAttributes) throws IOException {

        String username = (String) session.getAttribute("username");
        String nickname = (String) session.getAttribute("nickname");

        if (username == null) {
            redirectAttributes.addFlashAttribute("error", "login_required");
            return "redirect:/user/login.do";
        }

        // BlogDTO 객체 직접 생성
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setBookTitle(bookTitle);
        blogDTO.setBookAuthor(bookAuthor);
        blogDTO.setBookPublisher(bookPublisher);
        blogDTO.setBookRating(bookRating);
        blogDTO.setTitle(title);
        blogDTO.setContent(content);
        blogDTO.setWriter(nickname);
        blogDTO.setUsername(username);

        // 파일 업로드 처리 (디버깅 코드 추가)
        if (imgFile != null && !imgFile.isEmpty()) {
            String uploadPath = session.getServletContext().getRealPath("/upload");
            log.info("업로드 경로: " + uploadPath);

            String fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                log.info("업로드 디렉토리 생성: " + created);
            }

            log.info("업로드 디렉토리 경로: " + uploadDir.getAbsolutePath());
            log.info("업로드 디렉토리 존재: " + uploadDir.exists());

            File dest = new File(uploadDir, fileName);
            imgFile.transferTo(dest);
            blogDTO.setImg("upload/" + fileName);
            log.info("이미지 저장 완료: " + dest.getAbsolutePath());
            log.info("웹 접근 경로: " + blogDTO.getImg());
        }

        boolean success = blogService.insertBlog(blogDTO);
        if (success) {
            redirectAttributes.addFlashAttribute("msg", "write_success");
            return "redirect:/blog/list.do";
        } else {
            redirectAttributes.addFlashAttribute("error", "글쓰기에 실패했습니다.");
            return "redirect:/blog/write.do";
        }
    }

    // 5. 블로그 수정 폼
    @GetMapping("/edit.do")
    public String editForm(@RequestParam("id") int id,
                           HttpSession session,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        String currentUsername = (String) session.getAttribute("username");
        String currentNickname = (String) session.getAttribute("nickname");

        if (currentUsername == null) {
            redirectAttributes.addFlashAttribute("error", "login_required");
            return "redirect:/user/login.do";
        }

        BlogDTO blog = blogService.getBlogById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("error", "not_found");
            return "redirect:/blog/list.do";
        }


        if (!blog.getWriter().equals(currentNickname)) {
            redirectAttributes.addFlashAttribute("error", "no_permission");
            return "redirect:/blog/view.do?id=" + id;
        }

        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    // 6. 블로그 수정 처리
    @PostMapping("/edit.do")
    public String edit(
            @RequestParam("id") int id,
            @RequestParam("bookTitle") String bookTitle,
            @RequestParam("bookAuthor") String bookAuthor,
            @RequestParam("bookPublisher") String bookPublisher,
            @RequestParam("bookRating") String bookRating,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "img", required = false) MultipartFile imgFile,
            HttpSession session,
            RedirectAttributes redirectAttributes) throws IOException {

        String currentUser = (String) session.getAttribute("username");
        String currentNickname = (String) session.getAttribute("nickname");

        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "login_required");
            return "redirect:/user/login.do";
        }

        BlogDTO existingBlog = blogService.getBlogById(id);
        if (existingBlog == null) {
            redirectAttributes.addFlashAttribute("error", "not_found");
            return "redirect:/blog/list.do";
        }


        if (!existingBlog.getWriter().equals(currentNickname)) {
            redirectAttributes.addFlashAttribute("error", "no_permission");
            return "redirect:/blog/view.do?id=" + id;
        }

        // BlogDTO 객체 생성
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(id);
        blogDTO.setBookTitle(bookTitle);
        blogDTO.setBookAuthor(bookAuthor);
        blogDTO.setBookPublisher(bookPublisher);
        blogDTO.setBookRating(bookRating);
        blogDTO.setTitle(title);
        blogDTO.setContent(content);
        blogDTO.setWriter(currentNickname);
        blogDTO.setUsername(currentUser);

        // 파일 업로드 처리 (디버깅 코드 추가)
        if (imgFile != null && !imgFile.isEmpty()) {
            String uploadPath = session.getServletContext().getRealPath("/upload");
            log.info("수정 - 업로드 경로: " + uploadPath);

            String fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                log.info("수정 - 업로드 디렉토리 생성: " + created);
            }

            File dest = new File(uploadDir, fileName);
            imgFile.transferTo(dest);
            blogDTO.setImg("upload/" + fileName);
            log.info("수정 - 이미지 저장 완료: " + dest.getAbsolutePath());
        } else {
            blogDTO.setImg(existingBlog.getImg());
        }

        boolean success = blogService.updateBlog(blogDTO);
        if (success) {
            redirectAttributes.addFlashAttribute("msg", "update_success");
            return "redirect:/blog/view.do?id=" + id;
        } else {
            redirectAttributes.addFlashAttribute("error", "수정에 실패했습니다.");
            return "redirect:/blog/edit.do?id=" + id;
        }
    }

    // 7. 블로그 삭제
    @GetMapping("/delete.do")
    public String delete(@RequestParam("id") int id,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {

        String currentUser = (String) session.getAttribute("username");
        String currentNickname = (String) session.getAttribute("nickname");

        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "login_required");
            return "redirect:/user/login.do";
        }

        BlogDTO existingBlog = blogService.getBlogById(id);
        if (existingBlog == null) {
            redirectAttributes.addFlashAttribute("error", "not_found");
            return "redirect:/blog/list.do";
        }


        if (!existingBlog.getWriter().equals(currentNickname)) {
            redirectAttributes.addFlashAttribute("error", "no_permission");
            return "redirect:/blog/view.do?id=" + id;
        }

        boolean success = blogService.deleteBlog(id);
        if (success) {
            redirectAttributes.addFlashAttribute("msg", "delete_success");
        } else {
            redirectAttributes.addFlashAttribute("error", "삭제에 실패했습니다.");
        }
        return "redirect:/blog/list.do";
    }
    private String getUploadPath() {
        // 프로젝트 루트 디렉토리 기준으로 upload 폴더 생성
        String projectPath = System.getProperty("user.dir");
        String uploadPath = projectPath + "/src/main/webapp/upload/";

        // Gradle 프로젝트 구조라면
        // String uploadPath = projectPath + "/src/main/resources/static/upload/";

        log.info("프로젝트 경로: " + projectPath);
        log.info("업로드 경로: " + uploadPath);

        return uploadPath;
    }

    // 파일 업로드 처리 부분
//    if (imgFile != null && !imgFile.isEmpty()) {
//        String uploadPath = getUploadPath();
//
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//
//        String fileName = System.currentTimeMillis() + "_" +
//                imgFile.getOriginalFilename().replaceAll("\\s+", "_");
//
//        File dest = new File(uploadDir, fileName);
//        imgFile.transferTo(dest);
//
//        // 저장 경로: 상대경로로 저장
//        blogDTO.setImg("upload/" + fileName);
//    }
}