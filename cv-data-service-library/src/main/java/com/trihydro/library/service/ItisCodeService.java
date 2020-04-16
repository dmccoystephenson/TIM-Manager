package com.trihydro.library.service;

import java.util.Arrays;
import java.util.List;

import com.trihydro.library.model.ItisCode;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ItisCodeService extends CvDataServiceLibrary {

	public List<ItisCode> selectAll() {
		String url = String.format("%s/itiscodes", config.getCvRestService());
		ResponseEntity<ItisCode[]> response = restTemplateProvider.GetRestTemplate().getForEntity(url,
				ItisCode[].class);
		return Arrays.asList(response.getBody());
	}

}