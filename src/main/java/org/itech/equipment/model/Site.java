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
 * Domain class for entity "Site"
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
@Table(name = "site")
public class Site implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "diis_code", length = 255)
	private String diisCode;

	@Column(name = "datim_name", length = 255)
	private String datimName;

	@Column(name = "datim_code", length = 255)
	private String datimCode;

	@Column(name = "city", nullable = false, length = 100)
	private String city;

	@Column(name = "longitude", nullable = true)
	private Float longitude;

	@Column(name = "latitude", nullable = true)
	private Float latitude;

	@Column(name = "district_id", nullable = false)
	private Integer districtId;

	@ManyToOne
	@JoinColumn(name = "district_id", referencedColumnName = "id", insertable = false, updatable = false)
	private District district;

	@OneToMany(mappedBy = "site")
	private List<Lab> listOfLab;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("|");
		sb.append(name);
		sb.append("|");
		sb.append(diisCode);
		sb.append("|");
		sb.append(datimName);
		sb.append("|");
		sb.append(datimCode);
		sb.append("|");
		sb.append(city);
		sb.append("|");
		sb.append(longitude);
		sb.append("|");
		sb.append(latitude);
		sb.append("|");
		sb.append(districtId);
		return sb.toString();
	}

}
