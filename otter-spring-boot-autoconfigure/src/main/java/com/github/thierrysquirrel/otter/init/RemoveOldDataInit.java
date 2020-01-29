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
import com.github.thierrysquirrel.otter.core.thread.execution.RemoveOldDataInitThreadExecution;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * ClassName: RemoveOldDataInit
 * Description:
 * date: 2020/1/3 19:37
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class RemoveOldDataInit {
	private OtterProperties otterProperties;
	private OtterRepositoryService otterRepositoryService;

	public RemoveOldDataInit(OtterProperties otterProperties, OtterRepositoryService otterRepositoryService) {
		this.otterProperties = otterProperties;
		this.otterRepositoryService = otterRepositoryService;
	}

	@PostConstruct
	public void init() {
		ScheduledThreadPoolExecutor removeOldDataInitThreadPool = ThreadPoolFactoryConstant.REMOVE_OLD_DATA_INIT_THREAD_POOL;
		RemoveOldDataInitThreadExecution removeOldDataInitThreadExecution = new RemoveOldDataInitThreadExecution(otterProperties.getRemoveOldDataTime(), otterProperties.getRemoveOldDataNumber(), otterRepositoryService);
		Integer removeOldDataInterval = otterProperties.getRemoveOldDataInterval();
		ThreadPoolExecutorExecution.statsTimingThread(removeOldDataInitThreadPool, removeOldDataInitThreadExecution, removeOldDataInterval, removeOldDataInterval);
	}
}
