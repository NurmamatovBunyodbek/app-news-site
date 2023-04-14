package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.exception.ResourceNotFoundException;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RegisterDto;
import uz.pdp.appnewssite.repository.LavozimRepo;
import uz.pdp.appnewssite.repository.UserRepo;
import uz.pdp.appnewssite.utils.AppConstant;

@Service
public class AuthService  implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    LavozimRepo lavozimRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(RegisterDto registerDto){

        if (!registerDto.getPassword().equals(registerDto.getPrepassword()))
            return new ApiResponse("Parollar mos emas",false);
       if (userRepo.existsByUsername(registerDto.getUsername()))
           return  new ApiResponse("Bunday username allaqachon ro'yxatdan o'tgan",false);
        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                lavozimRepo.findByName(AppConstant.USER).orElseThrow(() ->
                        new ResourceNotFoundException("lavozim","name",AppConstant.USER)),
                true

        );
        userRepo.save(user);
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz",true);
    }

    public UserDetails  loadUserByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

    }



}
