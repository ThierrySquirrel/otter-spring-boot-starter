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

package com.github.thierrysquirrel.otter.interceptor.butterfly;

import com.github.thierrysquirrel.butterfly.annotation.ButterflyFilter;
import com.github.thierrysquirrel.butterfly.core.domain.PineRequestContextFilterDomain;
import com.github.thierrysquirrel.butterfly.core.filter.Filter;
import com.github.thierrysquirrel.otter.core.constant.InterceptorConstant;
import com.github.thierrysquirrel.otter.core.utils.GlobalIdUtils;

import java.util.Optional;

/**
 * ClassName: ButterConsumerFilter
 * Description:
 * date: 2020/1/30 1:51
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@ButterflyFilter
public class ButterConsumerFilter implements Filter {
	@Override
	public void filter(PineRequestContextFilterDomain pineRequestContextFilterDomain) {
		Optional<Long> globalId = Optional.ofNullable(GlobalIdUtils.getId());
		globalId.ifPresent(id -> pineRequestContextFilterDomain.setAttachment(InterceptorConstant.INTERCEPTOR_IDENTIFIER.getValue(), String.valueOf(id)));
	}
}
