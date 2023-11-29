package com.example.hobbybungae2.domain.comment.dto;

import com.example.hobbybungae2.domain.comment.entity.Comment;
import com.example.hobbybungae2.domain.user.entity.User;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long postId;

    private Long commentId;

    private String text;

    private String idName;

    public CommentResponseDto(Comment saveComment) {
        this.postId = saveComment.getPost().getId();
        this.commentId = saveComment.getId();
        this.text = saveComment.getText();
        this.idName = saveComment.getUser().getIdName();
    }
}
