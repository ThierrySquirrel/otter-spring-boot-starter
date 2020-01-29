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

package com.github.thierrysquirrel.otter.core.factory.execution;

import com.github.thierrysquirrel.otter.core.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.otter.core.domian.MethodDomain;
import com.github.thierrysquirrel.otter.core.thread.execution.RepairThreadExecution;
import com.github.thierrysquirrel.otter.core.utils.GlobalIdUtils;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Iterator;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: InterceptRequestExecution
 * Description:
 * date: 2020/1/3 17:51
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class InterceptRequestExecution {
	private InterceptRequestExecution() {
	}

	public static Object tryIntercept(String methodName, Object[] parameter, OtterRepositoryService otterRepositoryService, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Long globalId = GlobalIdUtils.createId();
		boolean modify = otterRepositoryService.saveOtterEntity(globalId, methodName, parameter);

		Object proceed = proceedingJoinPoint.proceed();
		if (modify) {
			otterRepositoryService.modifyOtterEntity(globalId);
		}

		return proceed;
	}

	public static void interceptError(MethodDomain methodDomain, Object[] parameter, Iterator<Integer> repairInterval, OtterRepositoryService otterRepositoryService) {
		Long id = GlobalIdUtils.getId();
		RepairThreadExecution repairThreadExecution = new RepairThreadExecution(id, methodDomain, parameter, repairInterval, otterRepositoryService);
		ThreadPoolExecutor repairThreadPool = ThreadPoolFactoryConstant.REPAIR_THREAD_POOL;
		ThreadPoolExecutorExecution.statsThread(repairThreadPool, repairThreadExecution);
	}

	public static Object release(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object proceed = proceedingJoinPoint.proceed();
		GlobalIdUtils.removeId();
		return proceed;
	}
}
