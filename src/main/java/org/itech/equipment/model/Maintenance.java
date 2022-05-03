/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.model;

import lombok.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Domain class for entity "Maintenance"
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
@Table(name = "maintenance")
public class Maintenance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "type", nullable = false, length = 100)
	private String type;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "end_date", nullable = true)
	private Date endDate;

	@Column(name = "lab_staff_name", nullable = true, length = 100)
	private String labStaffName;

	@Column(name = "lab_staff_contact", nullable = true, length = 45)
	private String labStaffContact;

	@Column(name = "intervention", nullable = true, length = 65535)
	private String intervention;

	@Column(name = "recommendatioon", nullable = true, length = 65535)
	private String recommendatioon;

	@Column(name = "lab_has_equipement_id", nullable = false)
	private Integer labHasEquipementId;
	@Column(name = "panne_id", nullable = true)
	private Integer panneId;

	@ManyToOne
	@JoinColumn(name = "lab_has_equipement_id", referencedColumnName = "id", insertable = false, updatable = false)
	private LabHasEquipement labHasEquipement;
	
	@ManyToOne
	@JoinColumn(name = "panne_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Panne panne;

}
