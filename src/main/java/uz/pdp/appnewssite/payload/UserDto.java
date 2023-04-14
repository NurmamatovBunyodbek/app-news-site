package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appnewssite.entity.Lavozim;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "FullName bo'sh bo'lmasligi kerak")
    private String fullName;
    @NotNull(message = "UserName bo'sh bo'lmasligi kerak")
    private String username;
    @NotNull(message = "password bo'sh bo'lmasligi kerak")
    private String password;
   //@NotNull(message = "LavozimId bo'sh bo'lmasligi kerak ")
    private Lavozim lavozimId;


}


