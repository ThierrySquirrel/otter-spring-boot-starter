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

/**
 * ClassName: AbstractRemoveOldDataInitThread
 * Description:
 * date: 2020/1/3 22:45
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public abstract class AbstractRemoveOldDataInitThread implements Runnable {
	private Integer removeOldDataTime;
	private Integer removeOldDataNumber;
	private OtterRepositoryService otterRepositoryService;

	public AbstractRemoveOldDataInitThread(Integer removeOldDataTime, Integer removeOldDataNumber, OtterRepositoryService otterRepositoryService) {
		this.removeOldDataTime = removeOldDataTime;
		this.removeOldDataNumber = removeOldDataNumber;
		this.otterRepositoryService = otterRepositoryService;
	}

	/**
	 * removeOldDataInit
	 *
	 * @param removeOldDataTime      removeOldDataTime
	 * @param removeOldDataNumber    removeOldDataNumber
	 * @param otterRepositoryService otterRepositoryService
	 */
	protected abstract void removeOldDataInit(Integer removeOldDataTime, Integer removeOldDataNumber, OtterRepositoryService otterRepositoryService);

	@Override
	public void run() {
		removeOldDataInit(this.removeOldDataTime,
				this.removeOldDataNumber,
				this.otterRepositoryService);
	}
}
