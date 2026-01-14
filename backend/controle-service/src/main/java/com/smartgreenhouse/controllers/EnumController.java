package com.smartgreenhouse.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo.ActionType;
import com.smartgreenhouse.enums.EquipementType;
import com.smartgreenhouse.enums.EtatEquipement;
import com.smartgreenhouse.enums.ParametreType;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/parametre-types")
    public ParametreType[] getParametreTypes() {
        return ParametreType.values();
    }
    @GetMapping("/equipement-types")
    public EquipementType[] getEquipementTypes() {
        return EquipementType.values();
    }
    @GetMapping("/equipement-etat")
    public EtatEquipement[] getEquipementEtat() {
        return EtatEquipement.values();
    }

    @GetMapping("/action-types")
    public ActionType[] getActionTypes() {
        return ActionType.values();
    }
}
