//package dev.adriangrzebyk.msvcbeerclient.web.config;
//
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateCustomizer;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
////@Component
//public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
//	private final int maxConnections;
//	private final int maxPerRoute;
//	private final int connectionTimeout;
//	private final int socketTimeout;
//
//	public BlockingRestTemplateCustomizer(@Value("${resttemplatecustomizer.maxconnections}") int maxConnections,
//	                                      @Value("${resttemplatecustomizer.maxperroute}")int maxPerRoute,
//	                                      @Value("${resttemplatecustomizer.connectiontimeout}")int connectionTimeout,
//	                                      @Value("${resttemplatecustomizer.sockettimeout}")int socketTimeout) {
//		this.maxConnections = maxConnections;
//		this.maxPerRoute = maxPerRoute;
//		this.connectionTimeout = connectionTimeout;
//		this.socketTimeout = socketTimeout;
//	}
//
//	private ClientHttpRequestFactory clientHttpRequestFactory() {
//		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//		connectionManager.setMaxTotal(maxConnections);
//		connectionManager.setDefaultMaxPerRoute(maxPerRoute);
//
//		RequestConfig requestConfig = RequestConfig
//				.custom()
//				.setConnectionRequestTimeout(connectionTimeout)
//				.setSocketTimeout(socketTimeout)
//				.build();
//
//		Htt httpClient = HttpClients
//				.custom()
//				.setConnectionManager(connectionManager)
//				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
//				.setDefaultRequestConfig(requestConfig)
//				.build();
//
//		return new HttpComponentsClientHttpRequestFactory(httpClient);
//	}
//
//
//	@Override
//	public void customize(RestTemplate restTemplate) {
//		restTemplate.setRequestFactory(this.clientHttpRequestFactory());
//	}
//}
