package com.example.hobbybungae2.domain.comment.service;

import com.example.hobbybungae2.domain.comment.dto.CommentRequestDto;
import com.example.hobbybungae2.domain.comment.dto.CommentResponseDto;
import com.example.hobbybungae2.domain.comment.entity.Comment;
import com.example.hobbybungae2.domain.comment.exception.InvalidCommentModifier;
import com.example.hobbybungae2.domain.comment.exception.NotFoundCommentException;
import com.example.hobbybungae2.domain.comment.exception.UnmatchedCommentPost;
import com.example.hobbybungae2.domain.comment.repository.CommentRepository;
import com.example.hobbybungae2.domain.post.entity.Post;
import com.example.hobbybungae2.domain.post.service.PostService;
import com.example.hobbybungae2.domain.user.entity.User;
import com.example.hobbybungae2.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;

	private final PostService postService;

	private final UserService userService;

	public List<CommentResponseDto> getComments(Long postId) {
		Post savePost = postService.getPostById(postId);

		List<CommentResponseDto> responseDtoList = savePost.getCommentList().stream().map(
			CommentResponseDto::new).toList();

		return responseDtoList;
	}

	public CommentResponseDto postComment(Long postId, CommentRequestDto requestDto, User user) {
		Post savePost = postService.getPostById(postId);
		User saveUser = userService.getUserEntity(user.getId());
		Comment comment = new Comment(requestDto, saveUser, savePost);

		Comment saveComment = commentRepository.save(comment);
		return new CommentResponseDto(saveComment);
	}

	@Transactional
	public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user) {
		Comment saveComment = getCommentById(commentId);
		checkPost(saveComment, postId);
		checkUser(saveComment, user.getIdName());

		saveComment.update(requestDto);
		return new CommentResponseDto(saveComment);
	}

	public void deleteComment(Long postId, Long commentId, User user) {
		Comment saveComment = getCommentById(commentId);
		checkPost(saveComment, postId);
		checkUser(saveComment, user.getIdName());

		commentRepository.delete(saveComment);
	}

	public Comment getCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElseThrow(
			() -> new NotFoundCommentException("comment's id", commentId.toString())
		);
	}

	public void checkPost(Comment comment, Long postId) {
		if (!comment.getPost().getId().equals(postId)) {
			throw new UnmatchedCommentPost("comment's postId", postId.toString());
		}
	}

	public void checkUser(Comment comment, String idName) {
		if (!comment.getUser().getIdName().equals(idName)) {
			throw new InvalidCommentModifier("comment's modifier", idName);
		}
	}
}
