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





/**
* Domain class for entity "PartnerHasLab"
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
@Table(name = "partner_has_lab")
public class PartnerHasLab implements Serializable {
 
private static final long serialVersionUID = 1L;

	@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id ;

    @Column(name="partner_id", nullable=false)
    private Integer partnerId ;

    @Column(name="lab_id", nullable=false)
    private Integer labId ;

    @Column(name="active", length=1)
    private String active ;

    @Column(name="partner_rep_name", nullable=false, length=255)
    private String partnerRepName ;

    @Column(name="partner_rep_contact", length=45)
    private String partnerRepContact ;

    @Column(name="partner_rep_mail", length=100)
    private String partnerRepMail ;

    @Temporal(TemporalType.DATE)
    @Column(name="start_date")
    private Date startDate ;

    @Temporal(TemporalType.DATE)
    @Column(name="end_date")
    private Date endDate ;


        @ManyToOne
    @JoinColumn(name="lab_id", referencedColumnName="id", insertable=false, updatable=false)
    private Lab        lab ; 

    @ManyToOne
    @JoinColumn(name="partner_id", referencedColumnName="id", insertable=false, updatable=false)
    private Partner    partner ; 


    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(partnerId);
        sb.append("|");
        sb.append(labId);
        sb.append("|");
        sb.append(active);
        sb.append("|");
        sb.append(partnerRepName);
        sb.append("|");
        sb.append(partnerRepContact);
        sb.append("|");
        sb.append(partnerRepMail);
        sb.append("|");
        sb.append(startDate);
        sb.append("|");
        sb.append(endDate);
        return sb.toString(); 
    } 

}



