/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
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
* Domain class for entity "Lab"
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
@Table(name = "lab")
public class Lab implements Serializable {
 
private static final long serialVersionUID = 1L;

	@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id ;

    @Column(name="lab_manager_name", nullable=true, length=255)
    private String labManagerName ;

    @Column(name="lab_manager_phone", nullable=true, length=100)
    private String labManagerPhone ;

    @Column(name="lab_manager_mail", nullable=true, length=45)
    private String labManagerMail ;

    @Column(name="electric_context", length=100)
    private String electricContext ;

    @Column(name="lab_type", nullable=false, length=100)
    private String labType ;

    @Column(name="active_staff_number", nullable=false)
    private Integer activeStaffNumber ;

    @Column(name="infrastructure_type", length=100)
    private String infrastructureType ;

    @Column(name="diem_staff_name", nullable=true, length=255)
    private String diemStaffName ;

    @Column(name="diem_staff_contact", nullable=true, length=45)
    private String diemStaffContact ;

    @Column(name="diem_staff_mail", nullable=true, length=100)
    private String diemStaffMail ;

    @Column(name="longitude")
    private Float longitude ;

    @Column(name="latitude")
    private Float latitude ;

    @Column(name="site_id", nullable=false)
    private Integer siteId ;


        @ManyToOne
    @JoinColumn(name="site_id", referencedColumnName="id", insertable=false, updatable=false)
    private Site       site ; 

    @OneToMany(mappedBy="lab")
    private List<LabHasEquipement> listOfLabHasEquipement ; 

    @OneToMany(mappedBy="lab")
    private List<PartnerHasLab> listOfPartnerHasLab ; 


    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(labManagerName);
        sb.append("|");
        sb.append(labManagerPhone);
        sb.append("|");
        sb.append(labManagerMail);
        sb.append("|");
        sb.append(electricContext);
        sb.append("|");
        sb.append(labType);
        sb.append("|");
        sb.append(activeStaffNumber);
        sb.append("|");
        sb.append(infrastructureType);
        sb.append("|");
        sb.append(diemStaffName);
        sb.append("|");
        sb.append(diemStaffContact);
        sb.append("|");
        sb.append(diemStaffMail);
        sb.append("|");
        sb.append(longitude);
        sb.append("|");
        sb.append(latitude);
        sb.append("|");
        sb.append(siteId);
        return sb.toString(); 
    } 

}



