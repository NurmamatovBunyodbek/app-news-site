package uz.pdp.appnewssite.payload;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {


    private String text;
    private String title;
    private String url;


}
