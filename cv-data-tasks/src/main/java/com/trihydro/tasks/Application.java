package com.trihydro.tasks;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.trihydro.library.service.CvDataServiceLibrary;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.trihydro.tasks.actions.CleanupActiveTims;
import com.trihydro.tasks.actions.RemoveExpiredActiveTims;
import com.trihydro.tasks.config.BasicConfiguration;

@SpringBootApplication
public class Application {

	protected static BasicConfiguration configuration;

	@Autowired
	public void setConfiguration(BasicConfiguration configurationRhs) {
		configuration = configurationRhs;
		CvDataServiceLibrary.setConfig(configuration);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void run() {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
		scheduledExecutorService.scheduleAtFixedRate(new RemoveExpiredActiveTims(configuration), 0, 4,
				TimeUnit.HOURS);
		scheduledExecutorService.scheduleAtFixedRate(new CleanupActiveTims(configuration), 0, 4,
				TimeUnit.HOURS);
	}
}