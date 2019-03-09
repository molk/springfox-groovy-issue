package net.marcusolk.springfoxissue.service

import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class AddressService {

	Map<String,String> normalise(Map<String,String> addressData) {
		def normalisedAddressData = [:] as Map<String,String>

		normalisedAddressData.with {
			if (addressData.houseNumber) {
				street      = addressData.street
				houseNumber = addressData.houseNumber
			}
			else {
				def (str, hno) = hnoSplit(addressData.street)

				street = str
				houseNumber = hno
			}

			zipCode = addressData.zipCode
			city    = addressData.city
			country = addressData.country ?: 'de'
		}

		return normalisedAddressData.collectEntries { name, value -> [name, toUpperCase(value)] }
	}

	private static String toUpperCase(String value) {
		value == null || value.empty ? '' : value.toUpperCase()
	}

	private static strHnoPattern = Pattern.compile '^(?<street>.*)\\s+(?<hno>\\d+[a-zA-Z]?)$'

	private static hnoSplit(String strHno)
		throws AddressNormalisationException {

		def matcher = strHnoPattern.matcher(strHno)

		if (!matcher.matches()) {
			throw new AddressNormalisationException(strHno)
		}

		[matcher.group('street'), matcher.group('hno')]*.toUpperCase()
	}

	static class AddressNormalisationException extends RuntimeException {
		AddressNormalisationException(String strHno) {
			super("Street and house number could not be normalised: $strHno")
		}
	}

}
