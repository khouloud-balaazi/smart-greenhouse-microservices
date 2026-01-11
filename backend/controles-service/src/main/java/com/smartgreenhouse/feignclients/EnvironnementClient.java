package com.smartgreenhouse.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smartgreenhouse.entities.ParametreDTO;
@FeignClient(name = "environment-service" ,url = "http://localhost:8089")
public interface EnvironnementClient {

    @GetMapping("/environment-service/api/parametres")
    List<ParametreDTO> getAllParametres();

    @GetMapping("/environment-service/api/parametres/{id}")
    ParametreDTO getParametreById(@PathVariable("id") Long id);
}
