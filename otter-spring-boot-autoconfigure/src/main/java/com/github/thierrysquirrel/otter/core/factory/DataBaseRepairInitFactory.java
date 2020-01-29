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
import com.github.thierrysquirrel.otter.core.thread.execution.RepairThreadExecution;
import com.github.thierrysquirrel.otter.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.otter.repository.entity.OtterEntityOutput;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;

import java.util.Iterator;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: DataBaseRepairInitFactory
 * Description:
 * date: 2020/1/3 20:11
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class DataBaseRepairInitFactory {
	private DataBaseRepairInitFactory() {
	}

	public static void dataBaseRepair(OtterEntityOutput otterEntityOutput, Iterator<Integer> repairInterval, OtterRepositoryService otterRepositoryService) {
		RepairThreadExecution repairThreadExecution = new RepairThreadExecution(otterEntityOutput.getId(), otterEntityOutput.getMethodDomain(), otterEntityOutput.getParameter(), repairInterval, otterRepositoryService);
		ThreadPoolExecutor dataBaseRepairThreadPool = ThreadPoolFactoryConstant.DATA_BASE_REPAIR_THREAD_POOL;
		ThreadPoolExecutorExecution.statsThread(dataBaseRepairThreadPool, repairThreadExecution);
	}
}
