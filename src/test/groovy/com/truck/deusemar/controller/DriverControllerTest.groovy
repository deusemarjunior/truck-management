package com.truck.deusemar.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration

import com.truck.deusemar.TruckApp
import com.truck.deusemar.domain.Driver
import com.truck.deusemar.domain.enums.GenderEnum
import com.truck.deusemar.domain.enums.TruckTypeEnum
import com.truck.deusemar.repository.DriverRepository

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(classes = TruckApp.class)
class DriverControllerTest extends Specification {	
	
	@Autowired
	DriverController controllerDriver
	
	@Autowired
	DriverRepository repository
	

	def 'should be return status 204 and no content with no drivers'() {
		when:
		def response = controllerDriver.list()
		
		then:
		response.statusCode == HttpStatus.NO_CONTENT
		!response.body
		
	}
	
	def 'should be return status 201 with JSON correct when drive was creeate'() {
		given:
		def driver = new Driver(null,'Asdrubal Norris',33, GenderEnum.MALE, true, TruckTypeEnum.TRUCK_EXTENDED )
		
		when:
		def response = controllerDriver.create(driver)
		
		then:
		response.statusCode == HttpStatus.CREATED
		response.body.properties == driver.properties
	}
	
	def 'should be return code 200 and get all drivers on correct format when there are drivers'() {
		given:
		def quantity = repository.count() 
		
		when:
		def response = controllerDriver.list()
		
		then:
		response.statusCode == HttpStatus.OK
		response.body.size() == quantity
		
		and:
		for (driver in response.body) {
			driver instanceof Driver			
		}
	}
	
}