package tk.topodifogna.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->
                exchange.getPrincipal()
                        .flatMap(p -> {
                                    logger.warn("Pre Routing Log - Routing {} to {}", p.getName(), exchange.getRequest().getPath());
                                    return Mono.empty();
                                }
                        )
                        .then(chain.filter(exchange)
                                .then(Mono.fromRunnable(() -> logger.warn("Post Routing Log"))));
    }

    public static class Config {

    }
}
