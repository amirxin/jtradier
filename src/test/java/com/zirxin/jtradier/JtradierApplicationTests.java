package com.zirxin.jtradier;

import com.zirxin.jtradier.config.AppConfig;
import com.zirxin.jtradier.dto.Quotes;
import com.zirxin.jtradier.service.RestTemplateProvider;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest({"tradier.token=", "tradier.url=https://sandbox.tradier.com/v1"})
public class JtradierApplicationTests {

	@Autowired
	private RestTemplateProvider provider;

	private Logger log = Logger.getRootLogger();

	public JtradierApplicationTests() {
	}

	@Test
	public void contextLoads() {
		HttpHeaders headers = provider.getHeaders();
		Assertions.assertThat(headers).isNotNull();
	}

	@SpringBootApplication
	@Import({AppConfig.class})
	static class TestConfiguration {
		TestConfiguration() {
		}
	}
}
