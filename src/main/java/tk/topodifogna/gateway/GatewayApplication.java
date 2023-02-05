package tk.topodifogna.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.R2dbcReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;

@SpringBootApplication
@EnableWebFluxSecurity
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	ReactiveOAuth2AuthorizedClientService authorizedClientService(
			DatabaseClient databaseClient,
			ReactiveClientRegistrationRepository clientRegistrationRepository) {
		return new R2dbcReactiveOAuth2AuthorizedClientService(databaseClient, clientRegistrationRepository);
	}

}
