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

package com.github.thierrysquirrel.otter.core.factory;

import com.github.thierrysquirrel.otter.core.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.otter.core.domian.MethodDomain;
import com.github.thierrysquirrel.otter.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * ClassName: RepairThreadFactory
 * Description:
 * date: 2020/1/3 18:23
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class RepairThreadFactory {
	private RepairThreadFactory() {
	}

	public static void tryRepair(Long id, MethodDomain methodDomain, Object[] objects, OtterRepositoryService otterRepositoryService) throws InvocationTargetException, IllegalAccessException {
		MethodDomainFactory.invoke(methodDomain, objects);
		otterRepositoryService.modifyOtterEntity(id);
	}

	public static void repairError(Runnable runnable, Iterator<Integer> retryInterval) {
		ScheduledThreadPoolExecutor repairRetry = ThreadPoolFactoryConstant.REPAIR_RETRY_THREAD_POOL;
		Integer next = retryInterval.next();
		log.info("Next Repair Time" + next + "Second");
		ThreadPoolExecutorExecution.statsDelayThread(repairRetry, runnable, next);
	}
}
