/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;
import org.itech.equipment.model.PartnerHasLab;

import java.util.List;
/**
* <h2>PartnerHasLabServiceimpl</h2>
*/
public interface PartnerHasLabService  {
PartnerHasLab create(PartnerHasLab d);
PartnerHasLab update(PartnerHasLab d);
PartnerHasLab getOne(int id) ;
 List<PartnerHasLab> getAll();
long getTotal();
boolean delete(int id);
}
