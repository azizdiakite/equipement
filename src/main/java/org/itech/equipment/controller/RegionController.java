/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import org.itech.equipment.model.Region;
import org.itech.equipment.service.RegionService;

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
@RequestMapping("/region")
public class RegionController {

	@Autowired
	private RegionService entityService;

	@PostMapping(value = "")
	public String createRegion(@Valid Region region) {
		try {
		entityService.createOrUpdate(region);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/region";
	}

	@GetMapping(value = "")
	public String getAllRegion(Model model) {
		List<Region> regions = entityService.getAll();
		model.addAttribute("regions", regions);
		model.addAttribute("region", new Region());
		model.addAttribute("mode", 0); //add a new entry
		return "organizations/region";
	}

	@GetMapping(value = "/{id}")
	public String getOneRegion(@PathVariable("id") int id, Model model) {
		List<Region> regions = entityService.getAll();
		Region e = entityService.getOne(id);
		if (e == null) {
			e = new Region();
		}
		model.addAttribute("regions", regions);
		model.addAttribute("mode", 1); //modify an entry
		model.addAttribute("region", e);
		return "organizations/region";
	}
	@GetMapping(value = "/delete/{id}")
	public String deleteRegion(@PathVariable("id") int id) {
		entityService.delete(id);
		return "redirect:/region";
	}

}
