package net.marcusolk.springfoxissue

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.stereotype.Component

@Component
class ServiceInfoContributor implements InfoContributor {

	private String serviceName, serviceVersion

	ServiceInfoContributor(
		@Value('${spring.application.name}') serviceName,
		@Value('${application.version}') serviceVersion) {
		this.serviceName = serviceName
		this.serviceVersion = serviceVersion
	}

	@Override
	void contribute(Info.Builder builder) {
		builder
			.withDetail('serviceName', serviceName)
			.withDetail('serviceVersion', serviceVersion)
	}

}
