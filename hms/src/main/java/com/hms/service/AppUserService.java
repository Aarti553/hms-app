package com.hms.service;
import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.payload.LoginDto;
import com.hms.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public AppUserService(AppUserRepository appUserRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public AppUser createAppUser(AppUser appUser) {
        AppUser savedEntity = appUserRepository.save(appUser);
        return savedEntity;
    }

    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser updateAppUser(Long id, AppUser appUser) {
        AppUser a = appUserRepository.findById(id).get();
        a.setName(appUser.getName());
        a.setUsername(appUser.getUsername());
        a.setEmail(appUser.getEmail());
        a.setPassword(appUser.getPassword());
        AppUser savedEntity = appUserRepository.save(a);
        return savedEntity;
    }

    public List<AppUser> getAllAppUsers() {
        List<AppUser> app = appUserRepository.findAll();
        return app;
    }

    public AppUserDto createAppUser(AppUserDto appUserDto) {
        // Copy dto to Entity
        AppUser appUser = new AppUser();
        appUser.setName(appUserDto.getName());
        appUser.setUsername(appUserDto.getUsername());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setPassword(appUserDto.getPassword());

        AppUser saveEntity = appUserRepository.save(appUser);

        //Copy entity to dto
        AppUserDto dto = new AppUserDto();
        dto.setName(saveEntity.getName());
        dto.setUsername(saveEntity.getUsername());
        dto.setEmail(saveEntity.getEmail());
        dto.setPassword(saveEntity.getPassword());

        return dto;
    }

    public AppUserDto createAppUserFromDto(AppUserDto appUserDto) {
        AppUser appUser = mapToEntity(appUserDto);
        AppUser savedEntity = appUserRepository.save(appUser);

        AppUserDto dto = mapToDto(savedEntity);
        return dto;
    }

//        AppUser mapToEntity(AppUserDto appUserDto){
//        AppUser appUser = new AppUser();
//        appUser.setUsername(appUserDto.getUsername());
//        appUser.setEmail(appUserDto.getEmail());
//        appUser.setName(appUserDto.getName());
//        appUser.setPassword(appUserDto.getPassword());
//        return appUser;
//        }

    AppUser mapToEntity(AppUserDto appUserDto) {
        AppUser map = modelMapper.map(appUserDto, AppUser.class);
        return map;
    }
//        AppUserDto mapToDto(AppUser appUser ){
//        AppUserDto dto = new AppUserDto();
//        dto.setUsername(appUser.getUsername());
//        dto.setEmail(appUser.getEmail());
//        dto.setName(appUser.getName());
//        dto.setPassword(appUser.getPassword());
//        return dto;
//        }

    AppUserDto mapToDto(AppUser appUser) {
        AppUserDto dto = modelMapper.map(appUser, AppUserDto.class);
        return dto;
    }


//    public boolean verifyLogin(LoginDto dto) {
//        Optional<AppUser> opUser = appUserRepository.findByUsername(dto.getUsername());
//        if (opUser.isPresent()) {
//            AppUser appUser = opUser.get();
//            return BCrypt.checkpw(dto.getPassword(), appUser.getPassword());
//        } else {
//            return false;
//        }
//    }

    public String verifyLogin(LoginDto dto){
        Optional<AppUser> opUser = appUserRepository.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            AppUser appUser = opUser.get();
            if(BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){ //this part only return token otherwise null
                //generate token
                String token = jwtService.generateToken(appUser.getUsername());
                return token;
            }
        }else{
            return null;
        }
        return null;
    }
}
/*
our code when you sign in successfully,is able to generate the token.
now we have to use this token for the subsequent request.whichever request further I will send it to the server
the request should be accepted only when the token sent with that request is valid.
* if the subsequent request made to the backend upon checking notice the token is invalid.
The request will not be processed.That's how we can secure all over URLS,Post login we can secure all ver URLs just
with concept of JWT token.And if you observe the whole thing there is no information of the token kept in the server site.
it is state less communication,because token has not saved anywhere in the backend.
* Our code simply verifying username,password,it generates a token and sends it back.
 */