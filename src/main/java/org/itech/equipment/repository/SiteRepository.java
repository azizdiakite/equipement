/*
 * Java domain class for entity "Site" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>SiteRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "Site" Repository
 */


public interface SiteRepository  extends JpaRepository<Site, Integer> , JpaSpecificationExecutor<Site> {

}
