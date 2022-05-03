/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

/**
 * Domain class for entity "Region"
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
@Table(name = "region")
public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nom", nullable = false, length = 255)
	private String nom;

	@Column(name = "longitude")
	private Float longitude;

	@Column(name = "latitude")
	private Float latitude;

	@OneToMany(mappedBy = "region")
	private List<District> listOfDistrict;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("|");
		sb.append(nom);
		sb.append("|");
		sb.append(longitude);
		sb.append("|");
		sb.append(latitude);
		return sb.toString();
	}

}
