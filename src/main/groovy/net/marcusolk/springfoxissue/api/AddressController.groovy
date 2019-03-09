package net.marcusolk.springfoxissue.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import net.marcusolk.springfoxissue.api.dto.Address
import net.marcusolk.springfoxissue.service.AddressService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import static org.springframework.http.ResponseEntity.status

@RestController
class AddressController {

	AddressService addressService

	AddressController(AddressService addressService) {
		this.addressService = addressService
	}

	@ApiOperation('Normalise Address')
	@ApiResponses([
		@ApiResponse(code = 200, message = 'Address successfully normalised'),
		@ApiResponse(code = 400, message = 'Bad request'),
		@ApiResponse(code = 500, message = 'Internal server error')
	])
	@PostMapping(
		path = '/addresses',
		consumes = APPLICATION_JSON_UTF8_VALUE,
		produces = APPLICATION_JSON_UTF8_VALUE
	)
	ResponseEntity<Address> normaliseAddress(@RequestBody Address address) {

		def normalisedAddress = fromMap addressService.normalise(asMap(address))

		return status(OK).body(normalisedAddress)
	}

	private static Map<String,String> asMap(object) {
		object.properties.findAll { it.key != 'class' }
	}

	private static Address fromMap(Map<String,String> address) {
		new Address(
			     street: address.street,
			houseNumber: address.houseNumber,
			    zipCode: address.zipCode,
			       city: address.city,
			    country: address.country
		)
	}

}
