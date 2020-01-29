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

package com.github.thierrysquirrel.otter.aspect;

import com.github.thierrysquirrel.otter.autoconfigure.OtterProperties;
import com.github.thierrysquirrel.otter.core.utils.AspectUtils;
import com.github.thierrysquirrel.otter.intercept.InterceptRequest;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * ClassName: OtterAspect
 * Description:
 * date: 2020/1/3 16:46
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Aspect
@Slf4j
@Data
public class OtterAspect {
	private OtterProperties otterProperties;
	private OtterRepositoryService otterRepositoryService;

	public OtterAspect(OtterProperties otterProperties, OtterRepositoryService otterRepositoryService) {
		this.otterProperties = otterProperties;
		this.otterRepositoryService = otterRepositoryService;
	}

	@Pointcut("@annotation(com.github.thierrysquirrel.otter.annotation.Repair)")
	public void repairAspect() {
		log.debug("RepairAspect");
	}

	@Around("repairAspect()")
	public Object repairPointcut(ProceedingJoinPoint point) throws Throwable {
		return InterceptRequest.intercept(otterRepositoryService, AspectUtils.getMethod(point).getName(), point.getArgs(), point, otterProperties);
	}

}
