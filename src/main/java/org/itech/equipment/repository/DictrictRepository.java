/*
 * Java domain class for entity "Dictrict" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>DictrictRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:24
 * <p>
 * Description: "Dictrict" Repository
 */


public interface DictrictRepository  extends JpaRepository<District, Integer> , JpaSpecificationExecutor<District> {

}
