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

package com.github.thierrysquirrel.otter.intercept;

import com.github.thierrysquirrel.otter.autoconfigure.OtterProperties;
import com.github.thierrysquirrel.otter.core.error.OtterException;
import com.github.thierrysquirrel.otter.core.factory.OtterContainerFactory;
import com.github.thierrysquirrel.otter.core.factory.execution.InterceptRequestExecution;
import com.github.thierrysquirrel.otter.core.utils.GlobalIdUtils;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * ClassName: InterceptRequest
 * Description:
 * date: 2020/1/3 16:48
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class InterceptRequest {
	private InterceptRequest() {
	}

	public static Object intercept(OtterRepositoryService otterRepositoryService, String methodName, Object[] parameter, ProceedingJoinPoint proceedingJoinPoint, OtterProperties otterProperties) throws Throwable {
		Long globalId = GlobalIdUtils.getId();
		if (StringUtils.isEmpty(globalId)) {
			try {
				return InterceptRequestExecution.tryIntercept(methodName, parameter, otterRepositoryService, proceedingJoinPoint);
			} catch (Exception e) {
				Iterator<Integer> iterator = Arrays.stream(otterProperties.getRepairInterval()).collect(Collectors.toList()).iterator();
				InterceptRequestExecution.interceptError(OtterContainerFactory.getMethodDomain(methodName), parameter, iterator, otterRepositoryService);
				throw new OtterException(e);
			} finally {
				GlobalIdUtils.removeId();
			}
		}
		return InterceptRequestExecution.release(proceedingJoinPoint);
	}
}
