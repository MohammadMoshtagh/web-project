package edu.sharif.webproject.authentication.enduser.entity.dto;

import edu.sharif.webproject.authentication.enduser.entity.EndUserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class EndUserDto {
    private String username;
    private EndUserRoleEnum role;
    private String enable;
    private Date createDate;
}
