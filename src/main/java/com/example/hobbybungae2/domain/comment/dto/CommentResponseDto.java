package com.example.hobbybungae2.domain.comment.dto;

import com.example.hobbybungae2.domain.comment.entity.Comment;
import com.example.hobbybungae2.domain.user.entity.User;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commentId;

    private String text;

    private String idName;

    public CommentResponseDto(Comment comment, User user) {
        this.commentId = comment.getId();
        this.text = comment.getText();
        this.idName = user.getIdName();
    }

    public CommentResponseDto(Comment saveComment) {
        this.text = saveComment.getText();
        this.idName = saveComment.getUser().getIdName();
    }
}
