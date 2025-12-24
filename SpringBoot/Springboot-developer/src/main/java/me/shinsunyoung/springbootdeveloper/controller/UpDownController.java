package me.shinsunyoung.springbootdeveloper.controller;

import me.shinsunyoung.springbootdeveloper.dto.UploadFileDTO;
import me.shinsunyoung.springbootdeveloper.util.FileNameUtil;
import me.shinsunyoung.springbootdeveloper.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class UpDownController {
    @Value("${org.zerock.upload.path}")
    private String uploadDir;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {
        FileUtil fileUtil = new FileUtil();
        fileUtil.uploadFile(uploadFileDTO);
        return null;
    }
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> view(@PathVariable String fileName){
        // upload폴더에 있는 파일이름을 찾아서 파일을 저장
        Resource resource = new FileSystemResource(uploadDir+ File.separator+fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        try{
            // 헤더에 파일 종류를 설정 : 이미지, 압축파일, 문서 등
            headers.add("Content-Type", Files.probeContentType((resource.getFile().toPath())));
        }catch(Exception e){
            e.printStackTrace();
            // 파일이 없거나 실행중 오류 발생시 실행
            return ResponseEntity.internalServerError().build();
        }
        // 정상 처리시 실행
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName){
        // 삭제 결과를 저장할 Map
        Map<String, Boolean> resultMap = new HashMap<>();
        FileUtil fileUtil = new FileUtil();
        boolean removed = fileUtil.fileRemove(fileName);
        // 정상 처리시 삭제 결과를 반환
        resultMap.put("result",removed);
        return resultMap;
    }
}
