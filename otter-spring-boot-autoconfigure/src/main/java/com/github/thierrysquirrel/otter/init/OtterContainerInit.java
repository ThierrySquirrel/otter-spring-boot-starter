/**
 * Copyright 2019 the original author or authors.
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

package com.github.thierrysquirrel.otter.init;

import com.github.thierrysquirrel.otter.annotation.Otter;
import com.github.thierrysquirrel.otter.annotation.Repair;
import com.github.thierrysquirrel.otter.core.factory.OtterContainerFactory;
import com.github.thierrysquirrel.otter.core.utils.AnnotatedMethodsUtils;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * ClassName: OtterContainerInit
 * Description:
 * date: 2020/1/3 16:31
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class OtterContainerInit implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		applicationContext.getBeansWithAnnotation(Otter.class).forEach((beanName, bean) ->
				AnnotatedMethodsUtils.getMethodAndAnnotation(bean, Repair.class).
						forEach((method, reset) -> OtterContainerFactory.addMethodDomain(method, bean))
		);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
