package com.ideacome.zuul.conf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ZuulHystrixConfig {

	@Bean
	public ZuulFallbackProvider getZuulFallbackProvider() {
		return new ZuulFallbackProvider0();
	}

	public static class ZuulFallbackProvider0 implements ZuulFallbackProvider {
		@Override
		public String getRoute() {
			return "*";
		}

		@Override
		public ClientHttpResponse fallbackResponse() {
			return new ClientHttpResponse() {
				/**
				 * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的， 不应该把api的404,500等问题抛给客户端
				 * 网关和api服务集群对于客户端来说是黑盒子
				 */
				@Override
				public HttpStatus getStatusCode() throws IOException {

					return HttpStatus.OK;
				}

				@Override
				public int getRawStatusCode() throws IOException {

					return HttpStatus.OK.value();
				}

				@Override
				public String getStatusText() throws IOException {

					return HttpStatus.OK.getReasonPhrase();
				}

				@Override
				public void close() {
				}

				/**
				 * 当 springms-provider-user 微服务出现宕机后，客户端再请求时候就会返回 fallback 等字样的字符串提示；
				 *
				 * 但对于复杂一点的微服务，我们这里就得好好琢磨该怎么友好提示给用户了；
				 * 
				 * 如果请求用户服务失败，返回什么信息给消费者客户端
				 * 
				 * @return
				 * @throws IOException
				 */
				@Override
				public InputStream getBody() throws IOException {
					JSONObject r = new JSONObject();
					try {
						r.put("state", "9999");
						r.put("msg", "系统错误，请求失败");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					log.error("出错啦~~~~~~~");
					return new ByteArrayInputStream(r.toString().getBytes("UTF-8"));
				}

				@Override
				public HttpHeaders getHeaders() {
					HttpHeaders headers = new HttpHeaders();
					// 和body中的内容编码一致，否则容易乱码
					headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
					return headers;
				}
			};
		}
	}
}
