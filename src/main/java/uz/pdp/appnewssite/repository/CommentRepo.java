package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment,Long> {


}
