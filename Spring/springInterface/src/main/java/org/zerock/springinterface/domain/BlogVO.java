package org.zerock.springinterface.domain;

import lombok.Data;

@Data
public class BlogVO {
    private Integer id;
    private String title;
    private String content;
    private String writer;
    private String username;
    private String img;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String bookRating;
    private String createdAt;
}