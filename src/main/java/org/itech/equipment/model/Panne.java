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
 * Domain class for entity "Panne"
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
@Table(name = "panne")
public class Panne implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date")
	private Date date;

	@Column(name = "reporter_name", length = 255)
	private String reporterName;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "report_date")
	private Date reportDate;

	@Column(name = "type", length = 100)
	private String type;
	
	@Column(name = "description", length = 255)
	private String description;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "contractor_inform_date")
	private Date contractorInformDate;

	@Column(name = "status", length = 45)
	private String status;
	
	@Column(name = "comment",  nullable = true, length = 255)
	private String comment;

	@Column(name = "lab_has_equipement_id", nullable = false)
	private Integer labHasEquipementId;

	@ManyToOne
	@JoinColumn(name = "lab_has_equipement_id", referencedColumnName = "id", insertable = false, updatable = false)
	private LabHasEquipement labHasEquipement;

}
