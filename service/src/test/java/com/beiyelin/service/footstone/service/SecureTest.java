package com.beiyelin.service.footstone.service;

import com.beiyelin.service.footstone.constant.SecureCST;
import com.beiyelin.service.footstone.secure.codec.HmacSHA256Utils;
import com.beiyelin.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xinsheng.hu on 2016/10/2.
 */
public class SecureTest extends BaseTest {
    private RestTemplate restTemplate = new RestTemplate();
    @Test
    public void testServiceHelloSuccess() {
        String username = "admin";
        String param11 = "param11";
        String param12 = "param12";
        String param2 = "param2";
        String key = "dadadswdewq2ewdwqdwadsadasd";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add(SecureCST.PARAM_USERNAME, username);
        params.add("param1", param11);
        params.add("param1", param12);
        params.add("param2", param2);
        params.add(SecureCST.PARAM_DIGEST, HmacSHA256Utils.digest(key, params));

        String url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/hello")
                .queryParams(params).build().toUriString();

        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        Assert.assertEquals("hello" + param11 + param12 + param2, responseEntity.getBody());
    }

}
