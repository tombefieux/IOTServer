package com.odysseycorp.homer.services;

import com.odysseycorp.homer.models.Controller;
import com.odysseycorp.homer.repositories.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ControllerService {
    private ControllerRepository controllerRepository;


    @Autowired
    public ControllerService(ControllerRepository controllerRepository){
        this.controllerRepository = controllerRepository;
    }

    private static Controller FetchData(String ip){

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("http:// " + ip  + ":80/Controller", Controller.class);
    }

    public List<Controller> getControllers(){
        List<Controller> Controllers = controllerRepository.findAll();
        for (int i = 0; i < Controllers.size(); i++) {
            Controllers.set(i, FetchData(Controllers.get(i).getIp()));
        }
        return Controllers;
    }

    public Controller getController(String idController){
        Optional<Controller> controller = controllerRepository.findById(idController);
        if(controller.isEmpty()){
            throw new RessourceNotFoundException();
        }
        return FetchData(controller.get().getIp());
    }

    public void addController(Controller newController){
        controllerRepository.save(newController);
    }

    public void deleteController(String idController){ controllerRepository.deleteById(idController); }

    public void updateController(String id, Controller updatedController){
        Controller oldController = getController(id);
        updatedController.setId(id);
        if(updatedController.getIp() == null) oldController.setIp(oldController.getIp());
        if(updatedController.getName() == null) oldController.setName(oldController.getName());
        if(updatedController.getMaxTemperature() == null) oldController.setIp(oldController.getIp());
        if(oldController.getIp() == null) oldController.setIp(oldController.getIp());
        if(oldController.getIp() == null) oldController.setIp(oldController.getIp());
        if(oldController.getIp() == null) oldController.setIp(oldController.getIp());


    }

}
