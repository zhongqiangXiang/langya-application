package com.ideacome.zuul.conf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ZuulHystrixConfig {
	
	@Bean
	public ZuulFallbackProvider getZuulFallbackProvider() {
		return new ZuulFallbackProvider();
	}

	public static class ZuulFallbackProvider implements FallbackProvider {
		@Override
		public String getRoute() {
			return "*";
		}

		@Override
		public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
			cause.printStackTrace();
			log.error("route:" + route);
			log.error("exception:" + cause.getMessage());
			return new ClientHttpResponse() {
				@Override
				public HttpStatus getStatusCode() throws IOException {
					return HttpStatus.OK;
				}

				@Override
				public int getRawStatusCode() throws IOException {
					return 200;
				}

				@Override
				public String getStatusText() throws IOException {
					return "ok";
				}

				@Override
				public void close() {

				}

				@Override
				public InputStream getBody() throws IOException {
					log.warn("oooops!error,i'm the fallback.");
					return new ByteArrayInputStream("oooops!error,i'm the fallback.".getBytes());
				}

				@Override
				public HttpHeaders getHeaders() {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					return headers;
				}
			};
		}
	}
}
