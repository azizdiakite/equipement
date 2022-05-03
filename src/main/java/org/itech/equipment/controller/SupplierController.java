/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Supplier;
import org.itech.equipment.service.SupplierService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService entityService;

	@PostMapping(value = "")
	public String createSupplier(@Valid Supplier supplier) {
		try {
			if(ObjectUtils.isEmpty(supplier.getContact())) {
				supplier.setContact(null);
			}
			if(ObjectUtils.isEmpty(supplier.getMail())) {
				supplier.setMail(null);
			}
			entityService.createOrUpdate(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/supplier";
	}

	@GetMapping(value = "")
	public String getAllSupplier(Model model) {
		List<Supplier> suppliers = entityService.getAll();
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("mode", 0); // add a new entry
		return "supplier/index";
	}

	@GetMapping(value = "/{id}")
	public String getOneSupplier(@PathVariable("id") int id, Model model) {
		List<Supplier> suppliers = entityService.getAll();
		Supplier e = entityService.getOne(id);
		if (e == null) {
			e = new Supplier();
		}
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("mode", 1); // modify an entry
		model.addAttribute("supplier", e);
		return "supplier/index";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") int id) {
		entityService.delete(id);
		return "redirect:/supplier";
	}

}
