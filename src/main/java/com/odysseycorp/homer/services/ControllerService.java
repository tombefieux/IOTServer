package com.odysseycorp.homer.services;


import com.odysseycorp.homer.exceptions.ResourceNotFoundException;
import com.odysseycorp.homer.models.Controller;
import com.odysseycorp.homer.models.requests.NameRequest;
import com.odysseycorp.homer.models.responses.SensorsResponse;
import com.odysseycorp.homer.repositories.ControllerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class ControllerService {
    private ControllerRepository controllerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerService.class);

    @Autowired
    public ControllerService(ControllerRepository controllerRepository){
        this.controllerRepository = controllerRepository;
    }

    private static Controller fetchData(String ip){
        RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(500)).build();
        return restTemplate.getForObject("http://" + ip  + ":80/controller", Controller.class);
    }

    public List<Controller> getControllers(){
        List<Controller> controllers = controllerRepository.findAll();
        for (int i = 0; i < controllers.size(); i++) {
            controllers.set(i, getController(controllers.get(i).getId()));
        }
        return controllers;
    }

    public Controller getController(Integer idController){
        Optional<Controller> dbController = controllerRepository.findById(idController);
        if(dbController.isEmpty()){
            throw new ResourceNotFoundException();
        }
        try {
            Controller result = fetchData(dbController.get().getIp());
            result.setIp(dbController.get().getIp());
            result.setId(dbController.get().getId());
            return result;
        } catch(Exception e) {
            LOGGER.debug("Cannot access to controller");
        }
        return dbController.get();
    }

    public Controller addController(Controller newController){
        Controller controller = controllerRepository.save(newController);

        // send update to controller
        NameRequest request = new NameRequest(newController.getName());
        RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(500)).build();
        try {
            restTemplate.put("http://" + controller.getIp() + ":80/controller", request);
        } catch(Exception e) {
            LOGGER.debug("Cannot access to new controller");
        }
        return controller;
    }

    public void deleteController(Integer idController) {
        controllerRepository.deleteById(idController);
    }

    public Controller updateController(Integer id, Controller updatedController){
        Controller oldController = getController(id);
        updatedController.setId(id);
        if(updatedController.getIp() != null) {
            oldController.setIp(updatedController.getIp());
        }
        if(updatedController.getName() != null) {
            oldController.setName(updatedController.getName());
        }
        controllerRepository.save(oldController);

        // send update to controller
        if(updatedController.isAlarmIsEnabled() != null) {
            oldController.setAlarmIsEnabled(updatedController.isAlarmIsEnabled());
        }
        if(updatedController.isUsePresenceSensor() != null) {
            oldController.setUsePresenceSensor(updatedController.isUsePresenceSensor());
        }
        if(updatedController.isUseTemperatureSensor() != null) {
            oldController.setUseTemperatureSensor(updatedController.isUseTemperatureSensor());
        }
        if(updatedController.getMinHumidity() != null) {
            oldController.setMinHumidity(updatedController.getMinHumidity());
        }
        if(updatedController.getMaxHumidity() != null) {
            oldController.setMaxHumidity(updatedController.getMaxHumidity());
        }
        if(updatedController.getMinTemperature() != null) {
            oldController.setMinTemperature(updatedController.getMinTemperature());
        }
        if(updatedController.getMaxTemperature() != null) {
            oldController.setMaxTemperature(updatedController.getMaxTemperature());
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://" + oldController.getIp()  + ":80/controller", oldController);
        return oldController;
    }

    public SensorsResponse getSensorsValue(Integer id) {
        Controller controller = getController(id);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://" + controller.getIp()  + ":80/sensors", SensorsResponse.class);
    }
}
