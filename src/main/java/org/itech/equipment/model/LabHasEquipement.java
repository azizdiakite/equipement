/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.model;

import lombok.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import java.util.List;

/**
 * Domain class for entity "LabHasEquipement"
 *
 * @author Pascal
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "lab_has_equipement")
public class LabHasEquipement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "lab_id", nullable = false)
	private Integer labId;

	@Column(name = "equipement_id", nullable = false)
	private Integer equipementId;

	@Column(name = "tag_number", length = 100)
	private String tagNumber;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "installation_date")
	private Date installationDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "warranty_start_date")
	private Date warrantyStartDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "warranty_end_date")
	private Date warrantyEndDate;

	@Column(name = "brand", length = 255)
	private String brand;

	@Column(name = "model", length = 255)
	private String model;

	@Column(name = "serial_number", length = 255)
	private String serialNumber;
	
	@Column(name = "location", length = 255, nullable = true)
	private String location;

	@Column(name = "supplier_id", nullable = true)
	private Integer supplierId;

	@ManyToOne
	@JoinColumn(name = "supplier_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Supplier supplier;

	@Column(name = "active", length = 1)
	private String active;

	@Column(name = "status", nullable = true)
	private int status = Equipement.STATUS_OK;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "release_date")
	private Date releaseDate;

	@Column(name = "contractor_id", nullable = true)
	private Integer contractorId;

	@ManyToOne
	@JoinColumn(name = "equipement_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Equipement equipement;

	@OneToMany(mappedBy = "labHasEquipement")
	private List<Maintenance> listOfMaintenance;

	@OneToMany(mappedBy = "labHasEquipement")
	private List<Panne> listOfPanne;

	@OneToMany(mappedBy = "labHasEquipement")
	private List<MaintenancePlanning> listOfMaintenancePlanning;

	@ManyToOne
	@JoinColumn(name = "lab_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Lab lab;

	@ManyToOne
	@JoinColumn(name = "contractor_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Contractor contractor;
	
	public String toString() {
		  StringBuilder sb = new StringBuilder(); 
	        sb.append(id);
	        sb.append("|");
	        sb.append(lab.getSite().getName());
	        sb.append("|");
	        sb.append(equipement.getEquipementName());
	        sb.append("|");
	        sb.append(serialNumber);
	        return sb.toString(); 
	}

}
