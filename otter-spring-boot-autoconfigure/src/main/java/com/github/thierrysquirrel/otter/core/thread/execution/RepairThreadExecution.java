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

package com.github.thierrysquirrel.otter.core.thread.execution;

import com.github.thierrysquirrel.otter.core.domian.MethodDomain;
import com.github.thierrysquirrel.otter.core.factory.RepairThreadFactory;
import com.github.thierrysquirrel.otter.core.thread.AbstractRepairThread;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * ClassName: RepairThreadExecution
 * Description:
 * date: 2020/1/3 18:13
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class RepairThreadExecution extends AbstractRepairThread {
	public RepairThreadExecution(Long id, MethodDomain methodDomain, Object[] parameter, Iterator<Integer> repairInterval, OtterRepositoryService otterRepositoryService) {
		super(id, methodDomain, parameter, repairInterval, otterRepositoryService);
	}

	@Override
	protected void otterRepairThread(Long id, MethodDomain methodDomain, Object[] parameter, Iterator<Integer> repairInterval, OtterRepositoryService otterRepositoryService) {
		if (repairInterval.hasNext()) {
			try {
				RepairThreadFactory.tryRepair(id, methodDomain, parameter, otterRepositoryService);
			} catch (Exception e) {
				RepairThreadFactory.repairError(this, repairInterval);
			}
		} else {
			log.info("Repair Failure");
		}
	}
}
