package org.itech.equipment.dto;

import org.itech.equipment.model.Equipement;
import org.itech.equipment.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaintenanceDTO {
	public int status = Equipement.STATUS_OK;
	public Maintenance maintenance;
}
