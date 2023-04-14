package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Comment> get() {
        List<Comment> comments = commentService.allCommentlist();
        return comments;
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody Comment comment) {
        ApiResponse apiResponse = commentService.addComment(comment);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody Comment comment) {
        ApiResponse apiResponse = commentService.editingComment(id, comment);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleted(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deletedComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


}
