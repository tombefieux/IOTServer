package com.odysseycorp.homer;

import com.odysseycorp.homer.models.Controller;
import com.odysseycorp.homer.models.Tracing;
import com.odysseycorp.homer.models.responses.SensorsResponse;
import com.odysseycorp.homer.repositories.TracingRepository;
import com.odysseycorp.homer.services.ControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

@SpringBootApplication
public class HomeRApplication {

	private final static int RECORD_TIME_INTERVAL_IN_SECONDS = 60;
	private final static Logger LOGGER = LoggerFactory.getLogger(HomeRApplication.class);

	@Autowired
	private ControllerService controllerService;
	@Autowired
	private TracingRepository tracingRepository;

	public static void main(String[] args) {
		SpringApplication.run(HomeRApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// start automatic recording
		new Thread(() -> {
			LOGGER.info("Recording thread started");
			while (true) {

				// wait
				try {
					Thread.sleep(RECORD_TIME_INTERVAL_IN_SECONDS * 1000);
				} catch (InterruptedException e) {
					LOGGER.error("Error when sleeping: ", e);
				}

				// record
				for (Controller c : controllerService.getControllers()) {
					SensorsResponse values = controllerService.getSensorsValue(c.getId());
					if(values != null && values.getHumidity() != null) {
						Tracing tracing = new Tracing();
						tracing.setController(c);
						tracing.setDate(new Date(Calendar.getInstance().getTime().getTime()));
						tracing.setTime(new Time(Calendar.getInstance().getTime().getTime()));
						tracing.setHumidity(values.getHumidity());
						tracing.setTemperature(values.getTemperature());
						tracingRepository.save(tracing);
					}
				}
			}
		}).start();
	}

}
