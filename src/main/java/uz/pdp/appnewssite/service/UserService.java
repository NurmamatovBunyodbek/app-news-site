package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.UserDto;
import uz.pdp.appnewssite.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public ApiResponse editUser(Long id , UserDto userDto){
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFullName(userDto.getFullName());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setLavozim(userDto.getLavozimId());
            userRepo.save(user);
            return new ApiResponse("User editing",true);
        }
        return new ApiResponse("User not found",true);
    }

    public ApiResponse addUser(UserDto userDto) {
        boolean byUsername = userRepo.existsByUsername(userDto.getUsername());
               if (byUsername)
                   return new ApiResponse("Bunday user mavjud",false);
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setLavozim(userDto.getLavozimId());
        userRepo.save(user);
        return new ApiResponse("Add User",true);
    }

    public ApiResponse deletedUser(Long id){
        userRepo.deleteById(id);
        return new ApiResponse("Deleted user",true);
    }
    public List<User> allUserList(){
        List<User> all = userRepo.findAll();
        return all;
    }

}
