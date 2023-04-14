package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.repository.LavozimRepo;

import java.util.List;
import java.util.Optional;

@Service
public class LavozimService {

    @Autowired
    LavozimRepo lavozimRepo;


    public ApiResponse addLavozim(LavozimDto lavozimDto) {

        if (lavozimRepo.existsByName(lavozimDto.getName()))
            return new ApiResponse("Bunday lavozim bor", false);
        Lavozim lavozim = new Lavozim();
        lavozim.setName(lavozimDto.getName());
        lavozim.setDescription(lavozimDto.getDescription());
        lavozim.setHuquqList(lavozimDto.getHuquqList());
        lavozimRepo.save(lavozim);
        return new ApiResponse("Saqlandi", true);
    }

    public ApiResponse editLavozim(Long id, LavozimDto lavozimDto) {
        Optional<Lavozim> optionalLavozim = lavozimRepo.findById(id);
        if (optionalLavozim.isPresent()) {
            Lavozim lavozim = optionalLavozim.get();
            lavozim.setName(lavozimDto.getName());
            lavozim.setDescription(lavozimDto.getDescription());
            lavozim.setHuquqList(lavozimDto.getHuquqList());
            lavozimRepo.save(lavozim);

            return new ApiResponse("Lavozim edit", true);
        }
        return new ApiResponse("Lavozim not found", false);
    }

    public ApiResponse deletedLavozim(Long id) {
        lavozimRepo.deleteById(id);
        return new ApiResponse("Lavozim deleted", true);
    }

    public List<Lavozim> getLavozim() {
        List<Lavozim> lavozimList = lavozimRepo.findAll();
        return lavozimList;
    }

}