package com.trihydro.odewrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.trihydro.library.model.ItisCode;
import com.trihydro.library.service.TimTypeService;
import com.trihydro.odewrapper.config.BasicConfiguration;
import com.trihydro.odewrapper.controller.WydotTimRcController;
import com.trihydro.odewrapper.helpers.SetItisCodes;
import com.trihydro.odewrapper.helpers.util.CreateBaseTimUtil;
import com.trihydro.odewrapper.model.ControllerResult;
import com.trihydro.odewrapper.model.TimRcList;
import com.trihydro.odewrapper.service.WydotTimService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class WydotTimRcControllerTest {

	@Mock
	BasicConfiguration mockBasicConfiguration;
	@Mock
	TimTypeService mockTimTypeService;
	@Mock
	WydotTimService mockWydotTimService;
	@Mock
	CreateBaseTimUtil mockCreateBaseTimUtil;
	@Mock
	SetItisCodes setItisCodes;

	@InjectMocks
	@Spy
	WydotTimRcController uut;

	private Gson gson = new Gson();

	@Before
	public void setup() throws Exception {
		List<ItisCode> itisCodes = new ArrayList<>();
		ItisCode ic = new ItisCode();
		ic.setCategoryId(-1);
		ic.setDescription("description");
		ic.setItisCode(-2);
		ic.setItisCodeId(-3);
		itisCodes.add(ic);
		List<String> itisCodesIncident = new ArrayList<>();
		itisCodesIncident.add("531");
		doReturn(itisCodesIncident).when(setItisCodes).setItisCodesRc(any());
		doReturn(itisCodes).when(setItisCodes).getItisCodes();

		doReturn(true).when(uut).routeSupported(isA(String.class));
	}

	@Test
	public void testCreateRcTim_bothDirections_success() throws Exception {

		// Arrange
		String rcJson = "{\"timRcList\": [{ \"route\": \"I80\", \"startPoint\": {\"latitude\": 41.161446, \"longitude\": -104.653162},\"endPoint\": {\"latitude\": 41.170465, \"longitude\": -104.085578}, \"roadCode\": \"LARI80WQDHLD\", \"direction\": \"b\",\"advisory\": [4871]} ]}";
		TimRcList timRcList = gson.fromJson(rcJson, TimRcList.class);

		// Act
		ResponseEntity<String> data = uut.createUpdateRoadConditionsTim(timRcList);

		// Assert
		assertEquals(HttpStatus.OK, data.getStatusCode());
		ControllerResult[] resultArr = gson.fromJson(data.getBody(), ControllerResult[].class);
		assertNotNull(resultArr);
		assertEquals(1, resultArr.length);
		assertEquals("success", resultArr[0].resultMessages.get(0));
		assertEquals("b", resultArr[0].direction);
		assertEquals("I80", resultArr[0].route);
	}

	@Test
	public void testCreateRcTim_bothDirections_NoMileposts() throws Exception {

		// Arrange
		String rcJson = "{\"timRcList\": [{ \"route\": \"I70\", \"roadCode\": \"LARI80WQDHLD\", \"startPoint\": {\"latitude\": 41.161446, \"longitude\": -104.653162},\"endPoint\": {\"latitude\": 41.170465, \"longitude\": -104.085578}, \"direction\":\"b\",\"advisory\": [4871]} ]}";
		TimRcList timRcList = gson.fromJson(rcJson, TimRcList.class);
		doReturn(false).when(uut).routeSupported("I70");

		// Act
		ResponseEntity<String> data = uut.createUpdateRoadConditionsTim(timRcList);

		// Assert
		assertEquals(HttpStatus.OK, data.getStatusCode());
		ControllerResult[] resultArr = gson.fromJson(data.getBody(), ControllerResult[].class);
		assertNotNull(resultArr);
		assertEquals(1, resultArr.length);
		assertEquals("route not supported", resultArr[0].resultMessages.get(0));
		assertEquals("b", resultArr[0].direction);
	}

	@Test
	public void testCreateRcTim_bothDirections_NoItisCodes() throws Exception {

		// Arrange
		String rcJson = "{\"timRcList\": [{ \"route\": \"I80\", \"startPoint\": {\"latitude\": 41.161446, \"longitude\": -104.653162},\"endPoint\": {\"latitude\": 41.170465, \"longitude\": -104.085578},\"roadCode\": \"LARI80WQDHLD\", \"direction\":\"b\",\"advisory\": [11]} ]}";
		TimRcList timRcList = gson.fromJson(rcJson, TimRcList.class);

		// Act
		ResponseEntity<String> data = uut.createUpdateRoadConditionsTim(timRcList);

		// Assert
		assertEquals(HttpStatus.OK, data.getStatusCode());
		ControllerResult[] resultArr = gson.fromJson(data.getBody(), ControllerResult[].class);
		assertNotNull(resultArr);
		assertEquals(1, resultArr.length);
		assertEquals("success", resultArr[0].resultMessages.get(0));
		assertEquals("b", resultArr[0].direction);
		assertEquals("I80", resultArr[0].route);
	}

	@Test
	public void testCreateRcTim_oneDirection_success() throws Exception {

		// Arrange
		String rcJson = "{\"timRcList\": [{ \"route\": \"I80\", \"startPoint\": {\"latitude\": 41.161446, \"longitude\": -104.653162},\"endPoint\": {\"latitude\": 41.170465, \"longitude\": -104.085578},\"roadCode\": \"LARI80WQDHLD\", \"direction\":\"i\",\"advisory\": [4871]} ]}";
		TimRcList timRcList = gson.fromJson(rcJson, TimRcList.class);

		// Act
		ResponseEntity<String> data = uut.createUpdateRoadConditionsTim(timRcList);

		// Assert
		assertEquals(HttpStatus.OK, data.getStatusCode());
		ControllerResult[] resultArr = gson.fromJson(data.getBody(), ControllerResult[].class);
		assertNotNull(resultArr);
		assertEquals(1, resultArr.length);
		assertEquals("success", resultArr[0].resultMessages.get(0));
		assertEquals("i", resultArr[0].direction);
		assertEquals("I80", resultArr[0].route);
	}

	@Test
	public void testCreateVslTim_oneDirection_NoMileposts() throws Exception {

		// Arrange
		String rcJson = "{\"timRcList\": [{ \"route\": \"I80\", \"startPoint\": {\"latitude\": 41.161446, \"longitude\": -104.653162},\"endPoint\": {\"latitude\": 41.170465, \"longitude\": -104.085578},\"roadCode\": \"LARI80WQDHLD\", \"direction\":\"i\",\"advisory\": [4871]} ]}";
		TimRcList timRcList = gson.fromJson(rcJson, TimRcList.class);

		// Act
		ResponseEntity<String> data = uut.createUpdateRoadConditionsTim(timRcList);

		// Assert
		assertEquals(HttpStatus.OK, data.getStatusCode());
		ControllerResult[] resultArr = gson.fromJson(data.getBody(), ControllerResult[].class);
		assertNotNull(resultArr);
		assertEquals(1, resultArr.length);
		assertEquals("success", resultArr[0].resultMessages.get(0));
		assertEquals("i", resultArr[0].direction);
		assertEquals("I80", resultArr[0].route);
	}

	@Test
	public void testCreateRcTim_oneDirection_NoItisCodes() throws Exception {

		// Arrange
		String rcJson = "{\"timRcList\": [{ \"route\": \"I80\", \"startPoint\": {\"latitude\": 41.161446, \"longitude\": -104.653162},\"endPoint\": {\"latitude\": 41.170465, \"longitude\": -104.085578},\"roadCode\": \"LARI80WQDHLD\", \"direction\":\"i\",\"advisory\": [11]} ]}";
		TimRcList timRcList = gson.fromJson(rcJson, TimRcList.class);

		// Act
		ResponseEntity<String> data = uut.createUpdateRoadConditionsTim(timRcList);

		// Assert
		assertEquals(HttpStatus.OK, data.getStatusCode());
		ControllerResult[] resultArr = gson.fromJson(data.getBody(), ControllerResult[].class);
		assertNotNull(resultArr);
		assertEquals(1, resultArr.length);
		assertEquals("success", resultArr[0].resultMessages.get(0));
		assertEquals("i", resultArr[0].direction);
		assertEquals("I80", resultArr[0].route);
	}

	@Test
	public void testAllClear() throws Exception {

		// Arrange
		String rcJson = "{\"timRcList\": [{ \"route\": \"I80\", \"startPoint\": {\"latitude\": 41.161446, \"longitude\": -104.653162},\"endPoint\": {\"latitude\": 41.170465, \"longitude\": -104.085578},\"roadCode\": \"LARI80WQDHLD\", \"direction\":\"d\",\"advisory\": [5378]} ]}";
		TimRcList timRcList = gson.fromJson(rcJson, TimRcList.class);

		// Act
		ResponseEntity<String> data = uut.submitAllClearRoadConditionsTim(timRcList);

		// Assert
		assertEquals(HttpStatus.OK, data.getStatusCode());
		ControllerResult[] resultArr = gson.fromJson(data.getBody(), ControllerResult[].class);
		assertNotNull(resultArr);
		assertEquals(1, resultArr.length);
		assertEquals("success", resultArr[0].resultMessages.get(0));
		assertEquals("d", resultArr[0].direction);
		assertEquals("I80", resultArr[0].route);
	}
}
