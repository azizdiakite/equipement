/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 */
package org.itech.equipment.model;

import lombok.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

 import java.util.List;


/**
* Domain class for entity "Contractor"
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
@Table(name = "contractor")
public class Contractor implements Serializable {
 
private static final long serialVersionUID = 1L;

	@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id ;

    @Column(name="name", nullable=false, length=100)
    private String name ;

    @Column(name="rep_name", nullable=true, length=255)
    private String repName ;

    @Column(name="rep_contact", nullable=true, length=45)
    private String repContact ;

    @Column(name="rep_mail", nullable=true, length=100)
    private String repMail ;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="contract_start_date")
    private Date contractStartDate ;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="contract_end_date")
    private Date contractEndDate ;


        @OneToMany(mappedBy="contractor")
    private List<LabHasEquipement> listOfLabHasEquipement ; 


    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(repName);
        sb.append("|");
        sb.append(repContact);
        sb.append("|");
        sb.append(repMail);
        sb.append("|");
        sb.append(contractStartDate);
        sb.append("|");
        sb.append(contractEndDate);
        return sb.toString(); 
    } 

}



