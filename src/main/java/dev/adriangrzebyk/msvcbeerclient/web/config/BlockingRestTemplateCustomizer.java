package dev.adriangrzebyk.msvcbeerclient.web.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
	private final int maxConnections;
	private final int maxPerRoute;
	private final long connectionTimeout;
	private final int socketTimeout;

	public BlockingRestTemplateCustomizer(@Value("${resttemplatecustomizer.maxconnections}") int maxConnections,
	                                      @Value("${resttemplatecustomizer.maxperroute}")int maxPerRoute,
	                                      @Value("${resttemplatecustomizer.connectiontimeout}")long connectionTimeout,
	                                      @Value("${resttemplatecustomizer.sockettimeout}")int socketTimeout) {
		this.maxConnections = maxConnections;
		this.maxPerRoute = maxPerRoute;
		this.connectionTimeout = connectionTimeout;
		this.socketTimeout = socketTimeout;
	}


	private ClientHttpRequestFactory clientHttpRequestFactory() {
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setConnectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
				.setSocketTimeout(socketTimeout, TimeUnit.MILLISECONDS)
				.build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setDefaultConnectionConfig(connectionConfig);
		connectionManager.setMaxTotal(maxConnections);
		connectionManager.setDefaultMaxPerRoute(maxPerRoute);

		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectionRequestTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
				.build();

		CloseableHttpClient httpClient = HttpClients
				.custom()
				.setConnectionManager(connectionManager)
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig)
				.build();

		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.setRequestFactory(this.clientHttpRequestFactory());
	}
}
