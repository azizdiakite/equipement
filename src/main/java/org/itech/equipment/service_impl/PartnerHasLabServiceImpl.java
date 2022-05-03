/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.PartnerHasLabRepository;
import org.itech.equipment.model.PartnerHasLab;
import org.itech.equipment.service.PartnerHasLabService;

import java.util.Collections;
import java.util.List;

/**
* <h2>PartnerHasLabServiceimpl</h2>
*/
@Service
@Transactional
public class PartnerHasLabServiceImpl implements PartnerHasLabService {

@Autowired
private PartnerHasLabRepository repository;

 @Override
    public PartnerHasLab create(PartnerHasLab d) {

       PartnerHasLab entity;

        try {
            entity = repository.save(d);

        } catch (Exception ex) {
            return null;
        }
        return entity;
    }

    
    @Override
    public PartnerHasLab update(PartnerHasLab d) {
        PartnerHasLab c;

        try {
            c = repository.saveAndFlush(d);

        } catch (Exception ex) {
            return null;
        }
        return c;
    }


    @Override
    public PartnerHasLab getOne(int id) {
        PartnerHasLab t;

        try {
            t = repository.findById(id).orElse(null);

        } catch (Exception ex) {
            return null;
        }
        return t;
    }


    @Override
    public List<PartnerHasLab> getAll() {
        List<PartnerHasLab> lst;

        try {
            lst = repository.findAll();

        } catch (Exception ex) {
            return Collections.emptyList();
        }
        return lst;
    }


    @Override
    public long getTotal() {
        long total;

        try {
            total = repository.count();
        } catch (Exception ex) {
            return 0;
        }
        return total;
    }


    @Override
    public boolean delete(int id) {
        try {
            repository.deleteById(id);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

   

}
