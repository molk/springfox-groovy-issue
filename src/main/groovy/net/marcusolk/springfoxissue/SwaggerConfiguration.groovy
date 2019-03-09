package net.marcusolk.springfoxissue

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.PropertyResolver
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import static java.util.Collections.emptyList
import static springfox.documentation.builders.PathSelectors.any
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage

@Configuration
@EnableSwagger2
@ConditionalOnWebApplication
@ConditionalOnProperty('dev.swagger.enabled')
class SwaggerConfiguration {

	@Bean
	Docket api(PropertyResolver propertyResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
			.useDefaultResponseMessages(false)
			.select()
				.apis(basePackage('net.marcusolk.springfoxissue.api'))
				.paths(any())
				.build()
			.apiInfo(new ApiInfo(
				// title
				'Address Service',

				// description
				'Address Service REST API',

				// version
				propertyResolver.getProperty('application.version', 'n.a.'),

				// terms of service URL
				'',

				// contact
				null,

				// license
				'',

				// license URL
				'',

				// vendor extensions
				emptyList()
			))
	}

}
