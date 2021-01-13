package com.odysseycorp.homer.controllers;

import com.odysseycorp.homer.models.Controller;
import com.odysseycorp.homer.models.responses.SensorsResponse;
import com.odysseycorp.homer.services.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controllers")
public class ControllerController {

    private final ControllerService controllerService;

    @Autowired
    public ControllerController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/{id}")
    public Controller getController(@PathVariable("id") Integer controllerId){
        return controllerService.getController(controllerId);
    }

    @GetMapping("/{id}/sensors")
    public SensorsResponse getSensors(@PathVariable("id") Integer controllerId){
        return controllerService.getSensorsValue(controllerId);
    }

    @PutMapping("/{id}")
    public void UpdateController(@PathVariable("id") Integer controllerId, @RequestBody Controller controller){
        controllerService.updateController(controllerId, controller);
    }

    @DeleteMapping("/{id}")
    public void DeleteController(@PathVariable("id") Integer controllerId){
        this.controllerService.deleteController(controllerId);
    }

    @PostMapping()
    public void AddController(@RequestBody Controller controller){
        this.controllerService.addController(controller);
    }

    @GetMapping
    public List<Controller> getControllers(){
        return controllerService.getControllers();
    }

}
