package uz.pdp.appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.Huquq;
import uz.pdp.appnewssite.repository.LavozimRepo;
import uz.pdp.appnewssite.repository.UserRepo;
import uz.pdp.appnewssite.utils.AppConstant;

import java.util.Arrays;

import static uz.pdp.appnewssite.entity.enums.Huquq.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;
    @Autowired
    LavozimRepo lavozimRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {

            Huquq[] values = Huquq.values();

            Lavozim admin = lavozimRepo.save(new Lavozim(
                    AppConstant.ADMIN,
                    Arrays.asList(values),
                    "Sistema egasi"
            ));
            Lavozim user = lavozimRepo.save(new Lavozim(
                    AppConstant.USER,
                    Arrays.asList(ADD_COMMIT, EDIT_COMMIT, DELETED_MY_COMMIT),
                    "Oddiy foydalanuvchi"
            ));
            userRepo.save(new User(
                    "Admin",
                    "Admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true

            ));

            userRepo.save(new User(
                    "User",
                    "User",
                    passwordEncoder.encode("user123"),
                    user,
                    true

            ));

        }


    }


}
