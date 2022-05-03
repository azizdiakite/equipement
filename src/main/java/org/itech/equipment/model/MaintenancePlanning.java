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
 * Domain class for entity "MaintenancePlanning"
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
@Table(name = "maintenance_planning")
public class MaintenancePlanning implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "lab_has_equipement_id", nullable = false)
	private Integer labHasEquipementId;

	@ManyToOne
	@JoinColumn(name = "lab_has_equipement_id", referencedColumnName = "id", insertable = false, updatable = false)
	private LabHasEquipement labHasEquipement;

}
