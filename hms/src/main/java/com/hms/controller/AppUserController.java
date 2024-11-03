package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<AppUser>createAppUser(
            @RequestBody AppUser appUser
    ){
        AppUser app = appUserService.createAppUser(appUser);
        return new ResponseEntity<>(app,HttpStatus.CREATED);
   }

    @PostMapping("/api/v1/appusers/dto")
public ResponseEntity<AppUserDto> creteAppUserFromDto(
        @RequestBody AppUserDto appUserDto
   ){
       AppUserDto appUserD = appUserService.createAppUserFromDto(appUserDto);
       return new ResponseEntity<>(appUserD,HttpStatus.CREATED);

   }
   @DeleteMapping
    public ResponseEntity<String>DeleteAppUser(
            @RequestParam Long id
   ){
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
   }
   @PutMapping("/{id}")
   public ResponseEntity<AppUser>updateAppUser(
           @PathVariable Long id,
           @RequestBody AppUser appUser
   ){
       AppUser appUserUp = appUserService.updateAppUser(id, appUser);
       return new ResponseEntity<>(appUserUp,HttpStatus.OK);
   }
   public List<AppUser>getAllAppUsers(){
       List<AppUser> allAppUsers = appUserService.getAllAppUsers();
       return allAppUsers;

   }
}