package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.aop.CheckPermission;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.service.LavozimService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {

    @Autowired
    LavozimService lavozimService;

    @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')")
    @PostMapping
    public HttpEntity<?> addLavozim(@Valid @RequestBody LavozimDto lavozimDto) {
        ApiResponse apiResponse = lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    //@PreAuthorize(value = "hasAuthority('EDIT_LAVOZIM')")
    @CheckPermission(huquq = "EDIT_LAVOZIM")
    @PutMapping("/{id}")
    public HttpEntity<?> editLavozim(
            @PathVariable Long id ,
            @Valid @RequestBody LavozimDto lavozimDto) {
        ApiResponse apiResponse = lavozimService.editLavozim(id,lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public List<Lavozim> get(){
        List<Lavozim> lavozimList = lavozimService.getLavozim();
        return lavozimList;
    }
    @DeleteMapping("/{id}")
    public  HttpEntity<?> deleted(@PathVariable Long id){
        ApiResponse apiResponse = lavozimService.deletedLavozim(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


}
