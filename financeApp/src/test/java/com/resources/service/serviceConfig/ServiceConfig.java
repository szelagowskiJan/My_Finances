package com.resources.service.serviceConfig;

import com.resources.dto.UserDto;
import com.resources.entity.Role;
import com.resources.entity.UserEntity;

/**
 *
 * @author Praca
 */
public class ServiceConfig {

    protected UserEntity getCorrectUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(3);
        userEntity.setFirstName("Adam");
        userEntity.setLastName("Nowak");
        userEntity.setEmail("nowak@wp.pl");
        userEntity.setPassword("4vgsd31s2131g");
        userEntity.setUserRole(1);
        userEntity.setEnabled(false);
        return userEntity;
    }

    protected UserEntity getUserEntityWithoutFirstNameAndLastName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(3);
        userEntity.setEmail("nowak@wp.pl");
        userEntity.setUserRole(1);
        userEntity.setEnabled(false);
        return userEntity;
    }

    protected UserEntity getUserEntityEmptyFirstNameAndLastName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(3);
        userEntity.setFirstName("");
        userEntity.setLastName("");
        userEntity.setEmail("nowak@wp.pl");
        userEntity.setUserRole(1);
        userEntity.setEnabled(false);
        return userEntity;
    }

    protected UserDto getCorrectUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserId(3);
        userDto.setFirstName("Adam");
        userDto.setLastName("Nowak");
        userDto.setEmail("nowak@wp.pl");
        userDto.setPassword("4vgsd31s2131g");
        userDto.setUserRole(1);
        userDto.setEnabled(false);
        return userDto;
    }

    protected Role getUserRole() {
        Role role = new Role();
        role.setId(1);
        role.setRoleName("USER");
        return role;
    }
}
