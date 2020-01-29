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

package com.github.thierrysquirrel.otter.core.thread;

import com.github.thierrysquirrel.otter.service.OtterRepositoryService;

import java.util.List;

/**
 * ClassName: AbstractDataBaseRepairInitThread
 * Description:
 * date: 2020/1/3 21:13
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public abstract class AbstractDataBaseRepairInitThread implements Runnable {
	private OtterRepositoryService otterRepositoryService;
	private List<Integer> repairInterval;
	private Integer dataBaseRepairNumber;

	public AbstractDataBaseRepairInitThread(OtterRepositoryService otterRepositoryService, List<Integer> repairInterval, Integer dataBaseRepairNumber) {
		this.otterRepositoryService = otterRepositoryService;
		this.repairInterval = repairInterval;
		this.dataBaseRepairNumber = dataBaseRepairNumber;
	}

	/**
	 * dataBaseRepairInit
	 *
	 * @param otterRepositoryService otterRepositoryService
	 * @param repairInterval         repairInterval
	 * @param dataBaseRepairNumber   dataBaseRepairNumber
	 */
	protected abstract void dataBaseRepairInit(OtterRepositoryService otterRepositoryService, List<Integer> repairInterval, Integer dataBaseRepairNumber);

	@Override
	public void run() {
		dataBaseRepairInit(this.otterRepositoryService,
				this.repairInterval,
				this.dataBaseRepairNumber);
	}
}
