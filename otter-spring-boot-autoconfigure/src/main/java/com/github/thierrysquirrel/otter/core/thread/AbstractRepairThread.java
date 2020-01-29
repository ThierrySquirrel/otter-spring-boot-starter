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

import com.github.thierrysquirrel.otter.core.domian.MethodDomain;
import com.github.thierrysquirrel.otter.service.OtterRepositoryService;

import java.util.Iterator;

/**
 * ClassName: AbstractRepairThread
 * Description:
 * date: 2020/1/3 18:06
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public abstract class AbstractRepairThread implements Runnable {
	private Long id;
	private MethodDomain methodDomain;
	private Object[] parameter;
	private Iterator<Integer> repairInterval;
	private OtterRepositoryService otterRepositoryService;

	public AbstractRepairThread(Long id, MethodDomain methodDomain, Object[] parameter, Iterator<Integer> repairInterval, OtterRepositoryService otterRepositoryService) {
		this.id = id;
		this.methodDomain = methodDomain;
		this.parameter = parameter;
		this.repairInterval = repairInterval;
		this.otterRepositoryService = otterRepositoryService;
	}

	/**
	 * otterRepairThread
	 *
	 * @param id                     id
	 * @param methodDomain           methodDomain
	 * @param parameter              parameter
	 * @param repairInterval         repairInterval
	 * @param otterRepositoryService otterRepositoryService
	 */
	protected abstract void otterRepairThread(Long id, MethodDomain methodDomain, Object[] parameter, Iterator<Integer> repairInterval, OtterRepositoryService otterRepositoryService);

	@Override
	public void run() {
		otterRepairThread(this.id,
				this.methodDomain,
				this.parameter,
				this.repairInterval,
				this.otterRepositoryService);
	}
}
