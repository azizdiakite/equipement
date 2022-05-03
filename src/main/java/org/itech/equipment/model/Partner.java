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
* Domain class for entity "Partner"
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
@Table(name = "partner")
public class Partner implements Serializable {
 
private static final long serialVersionUID = 1L;

	@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id ;

    @Column(name="partner_name", nullable=false, length=100)
    private String partnerName ;

    @Column(name="active", length=1)
    private String active ;


        @OneToMany(mappedBy="partner")
    private List<PartnerHasLab> listOfPartnerHasLab ; 


    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(partnerName);
        sb.append("|");
        sb.append(active);
        return sb.toString(); 
    } 

}



