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

import com.github.thierrysquirrel.otter.autoconfigure.OtterProperties;
import com.github.thierrysquirrel.otter.core.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.otter.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.otter.core.thread.execution.DataBaseRepairInitThreadExecution;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * ClassName: DataBaseRepairInit
 * Description:
 * date: 2020/1/3 19:37
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class DataBaseRepairInit {
	private OtterProperties otterProperties;
	private OtterRepositoryService otterRepositoryService;

	public DataBaseRepairInit(OtterProperties otterProperties, OtterRepositoryService otterRepositoryService) {
		this.otterProperties = otterProperties;
		this.otterRepositoryService = otterRepositoryService;
	}

	@PostConstruct
	public void init() {
		List<Integer> repairInterval = Arrays.stream(otterProperties.getRepairInterval()).collect(Collectors.toList());
		DataBaseRepairInitThreadExecution dataBaseRepairInitThreadExecution = new DataBaseRepairInitThreadExecution(otterRepositoryService, repairInterval, otterProperties.getDataBaseRepairNumber());
		ScheduledThreadPoolExecutor dataBaseRepairInit = ThreadPoolFactoryConstant.DATA_BASE_REPAIR_INIT_THREAD_POOL;
		Integer dataBaseRepairTime = otterProperties.getDataBaseRepairTime();
		ThreadPoolExecutorExecution.statsTimingThread(dataBaseRepairInit, dataBaseRepairInitThreadExecution, dataBaseRepairTime, dataBaseRepairTime);
	}
}
