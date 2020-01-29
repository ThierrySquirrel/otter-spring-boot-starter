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

package com.github.thierrysquirrel.otter.core.constant;

/**
 * ClassName: ThreadPoolSizeConstant
 * Description:
 * date: 2020/1/3 18:26
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum ThreadPoolSizeConstant {
	/**
	 * RepairRetryCorePoolSize
	 */
	REPAIR_RETRY_CORE_POOL_SIZE(Runtime.getRuntime().availableProcessors() * 2),
	/**
	 * RepairCorePoolSize
	 */
	REPAIR_CORE_POOL_SIZE(Runtime.getRuntime().availableProcessors() * 2),
	/**
	 * RepairMaxiMumPoolSize
	 */
	REPAIR_MAXI_MUM_POOL_SIZE(Runtime.getRuntime().availableProcessors() * 2),
	/**
	 * RepairKeepAliveTime
	 */
	REPAIR_KEEP_ALIVE_TIME(0),
	/**
	 * RepairCapacity
	 */
	REPAIR_CAPACITY(4096),
	/**
	 * DataBaseRepairCorePoolSize
	 */
	DATA_BASE_REPAIR_CORE_POOL_SIZE(Runtime.getRuntime().availableProcessors() * 2),
	/**
	 * DataBaseRepairMaxiMumPoolSize
	 */
	DATA_BASE_REPAIR_MAXI_MUM_POOL_SIZE(Runtime.getRuntime().availableProcessors() * 2),
	/**
	 * DataBaseRepairKeepAliveTime
	 */
	DATA_BASE_REPAIR_KEEP_ALIVE_TIME(0),
	/**
	 * DataBaseRepairCapacity
	 */
	DATA_BASE_REPAIR_CAPACITY(4096),
	/**
	 * DataBaseRepairInitCorePoolSize
	 */
	DATA_BASE_REPAIR_INIT_CORE_POOL_SIZE(1),
	/**
	 * RemoveOldDataInitCorePoolSize
	 */
	REMOVE_OLD_DATA_INIT_CORE_POOL_SIZE(1);

	private int value;

	ThreadPoolSizeConstant(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
