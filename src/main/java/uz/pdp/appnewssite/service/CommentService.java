package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.repository.CommentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    public List<Comment> allCommentlist(){
        List<Comment> commentList = commentRepo.findAll();
        return commentList;
    }

    public ApiResponse addComment(Comment comment){
        Comment comment1 = new Comment();
        comment1.setText(comment.getText());
        comment1.setPost(comment.getPost());
        commentRepo.save(comment1);
        return new ApiResponse("Add commment",true);
    }

    public ApiResponse editingComment(Long id , Comment comment){
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if (optionalComment.isPresent()){
            Comment comment1 = optionalComment.get();
            comment1.setText(comment.getText());
            comment1.setPost(comment.getPost());
            commentRepo.save(comment1);
            return new ApiResponse("Comment editing",true);
        }
        return new ApiResponse("Comment not found",false);
    }

    public ApiResponse deletedComment(Long id){
        commentRepo.deleteById(id);
        return new ApiResponse("Comment deleted",true);
    }
}
