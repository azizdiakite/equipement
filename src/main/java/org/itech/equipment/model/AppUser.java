/* 
 * Created on 2021-07-09 ( Date ISO 2021-07-09 - Time 06:18:50 )
 */
package org.itech.equipment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Domain class for entity "AppUser"
 *
 * @author Pascal
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {
	public static String PASSWORD_RESET_NO = "N";
	public static String PASSWORD_RESET_YES = "Y";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "email", nullable = false, length = 255)
	private String email;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "active", length = 1)
	private boolean active = true;

	@Column(name = "locked", length = 1)
	private boolean locked = false;

	@Column(name = "first_name", length = 45)
	private String firstName;

	@Column(name = "last_name", length = 45)
	private String lastName;

	@Column(name = "phone_contact", nullable = false, length = 45)
	private String phoneContact;
	
	@Column(name = "role", length = 45)
	private String role;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date lastLogin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "password_expire_at")
	private Date passwordExpireAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_at")
	private Date lastUpdatedAt;

	@Column(name = "last_updated_by")
	private Integer lastUpdatedBy;

	public String getAuthorities() {
		return this.getRole();
	}

}
