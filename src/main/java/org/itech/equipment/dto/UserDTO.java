package org.itech.equipment.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Integer id;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneContact;
	private String password;
	private String repassword;
	private boolean active = true;
	private boolean locked = false;
	private Date passwordExpireAt;
	private String role;
}
