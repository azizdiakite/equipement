/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 */
package org.itech.equipment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Domain class for entity "Equipement"
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
@Table(name = "equipement")
public class Equipement implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer STATUS_OUT = 0;
	public static final Integer STATUS_OK = 1;
	public static final Integer STATUS_BREAK = 2;
	public static final Integer STATUS_CURRENT_MAINTENANCE = 3;
	public static final Integer STATUS_MOVED = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "equipement_name", nullable = false, length = 255)
	private String equipementName;

	@Column(name = "category", length = 255)
	private String category;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("|");
		sb.append(equipementName);
		sb.append("|");
		sb.append(category);
		sb.append("|");
		return sb.toString();
	}

}
