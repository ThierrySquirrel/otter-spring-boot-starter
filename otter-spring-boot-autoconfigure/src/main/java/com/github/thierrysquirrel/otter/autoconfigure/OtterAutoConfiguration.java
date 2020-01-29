/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.otter.autoconfigure;

import com.github.thierrysquirrel.otter.aspect.OtterAspect;
import com.github.thierrysquirrel.otter.core.constant.ComponentScanConstant;
import com.github.thierrysquirrel.otter.init.DataBaseRepairInit;
import com.github.thierrysquirrel.otter.init.OtterContainerInit;
import com.github.thierrysquirrel.otter.init.RemoveOldDataInit;
import com.github.thierrysquirrel.otter.interceptor.spring.OtterFeignRequestInterceptor;
import com.github.thierrysquirrel.otter.interceptor.spring.OtterOncePerRequestFilter;
import com.github.thierrysquirrel.otter.repository.OtterRepository;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resource;

/**
 * ClassName: OtterAutoConfiguration
 * Description:
 * date: 2020/1/3 15:35
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties(OtterProperties.class)
@EnableJpaRepositories(basePackages = ComponentScanConstant.JPA_REPOSITORIES_SCAN)
@EntityScan(basePackages = ComponentScanConstant.ENTITY_SCAN)
@EnableDubbo(scanBasePackages = ComponentScanConstant.DUBBO_SCAN)
@ComponentScan(basePackages = {ComponentScanConstant.BUTTER_SCAN})
public class OtterAutoConfiguration {

	@Resource
	private OtterProperties otterProperties;
	@Resource
	private OtterRepository otterRepository;

	@Bean
	@ConditionalOnMissingBean(OtterFeignRequestInterceptor.class)
	public OtterFeignRequestInterceptor otterFeignRequestInterceptor() {
		return new OtterFeignRequestInterceptor();
	}

	@Bean
	@ConditionalOnMissingBean(OtterOncePerRequestFilter.class)
	public OtterOncePerRequestFilter otterOncePerRequestFilter() {
		return new OtterOncePerRequestFilter();
	}

	@Bean
	@ConditionalOnMissingBean(OtterAspect.class)
	public OtterAspect otterAspect() {
		return new OtterAspect(otterProperties, new OtterRepositoryService(otterRepository));
	}

	@Bean
	@ConditionalOnMissingBean(DataBaseRepairInit.class)
	public DataBaseRepairInit dataBaseRepairInit() {
		return new DataBaseRepairInit(otterProperties, new OtterRepositoryService(otterRepository));
	}

	@Bean
	@ConditionalOnMissingBean(OtterContainerInit.class)
	public OtterContainerInit otterContainerInit() {
		return new OtterContainerInit();
	}

	@Bean
	@ConditionalOnMissingBean(RemoveOldDataInit.class)
	public RemoveOldDataInit removeOldDataInit() {
		return new RemoveOldDataInit(otterProperties, new OtterRepositoryService(otterRepository));
	}
}
