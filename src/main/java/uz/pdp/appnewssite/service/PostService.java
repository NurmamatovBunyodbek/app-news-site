package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.repository.PostRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    public ApiResponse addPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepo.save(post);
        return new ApiResponse("Add post", true);
    }

    public ApiResponse editPost(Long id, PostDto postDto) {
        Optional<Post> optionalPost = postRepo.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(postDto.getTitle());
            post.setText(postDto.getText());
            post.setUrl(postDto.getUrl());
            postRepo.save(post);
            return new ApiResponse("Post editing", true);
        }
        return new ApiResponse("Post not found", false);
    }

    public ApiResponse deletedPost(Long id) {
        postRepo.deleteById(id);
        return new ApiResponse("Post deleted", true);
    }

}
