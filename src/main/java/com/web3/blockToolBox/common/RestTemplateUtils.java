package com.web3.blockToolBox.common;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestTemplateUtils {

    private static final RestTemplate REST_TEMPLATE = initRestTemplate();

    private static RestTemplate initRestTemplate() {
        //生成一个设置了连接超时时间、请求超时时间、异常最大重试次数的httpClient
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(60000).build();
        HttpClientBuilder builder = HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(50)
                .setDefaultRequestConfig(config).setRetryHandler(new DefaultHttpRequestRetryHandler(5, false));
        HttpClient httpClient = builder.build();
        //使用httpClient创建一个ClientHttpRequestFactory的实现
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        //ClientHttpRequestFactory作为参数构造一个使用作为底层的RestTemplate
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        //获取RestTemplate默认配置好的所有转换器
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        //解决中文编码问题
        messageConverters.removeIf(converter -> converter instanceof StringHttpMessageConverter
                || converter instanceof MappingJackson2HttpMessageConverter);
        messageConverters.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        //使用FastJSON转换器
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        messageConverters.add(6, fastJsonConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    public static <T> T postForObject(String url, String params, HttpHeaders headers, Class<T> responseType) {
        HttpEntity<String> entity = new HttpEntity<>(params, headers);
        return REST_TEMPLATE.exchange(url, HttpMethod.POST, entity, responseType).getBody();
    }

    public static <T> T postForJsonObject(String url, Map<String, ?> params, Map<String, String> headers, Class<T> responseType) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        HttpEntity<Map<String, ?>> entity = new HttpEntity<>(params, httpHeaders);
        return REST_TEMPLATE.exchange(url, HttpMethod.POST, entity, responseType).getBody();
    }

    public static <T> T getForObject(String url, Map<String, ?> uriVariables, Map<String, String> headers, Class<T> responseType) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        HttpEntity<T> entity = new HttpEntity<>(httpHeaders);
        if (uriVariables.isEmpty()){
            return REST_TEMPLATE.exchange(url, HttpMethod.GET, entity, responseType).getBody();
        } else {
            return REST_TEMPLATE.exchange(url, HttpMethod.GET, entity, responseType, uriVariables).getBody();
        }
    }

    private static HttpHeaders getHttpHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpHeaders.add(key, headers.get(key));
            }
        }
        return httpHeaders;
    }


}
