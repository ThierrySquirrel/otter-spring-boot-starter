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

package com.github.thierrysquirrel.otter.interceptor.spring;

import com.github.thierrysquirrel.otter.core.constant.InterceptorConstant;
import com.github.thierrysquirrel.otter.core.utils.GlobalIdUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Optional;

/**
 * ClassName: OtterFeignRequestInterceptor
 * Description:
 * date: 2020/1/3 16:06
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class OtterFeignRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		Optional<Long> globalId = Optional.ofNullable(GlobalIdUtils.getId());
		globalId.ifPresent(id -> template.header(InterceptorConstant.INTERCEPTOR_IDENTIFIER.getValue(), String.valueOf(id)));
	}
}
