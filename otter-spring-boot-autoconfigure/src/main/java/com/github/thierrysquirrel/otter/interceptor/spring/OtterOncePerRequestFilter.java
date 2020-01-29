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
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * ClassName: OtterOncePerRequestFilter
 * Description:
 * date: 2020/1/3 16:07
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class OtterOncePerRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Optional<String> globalId = Optional.ofNullable(request.getHeader(InterceptorConstant.INTERCEPTOR_IDENTIFIER.getValue()));
		globalId.ifPresent(id -> GlobalIdUtils.setId(Long.valueOf(id)));
		filterChain.doFilter(request, response);
	}
}
