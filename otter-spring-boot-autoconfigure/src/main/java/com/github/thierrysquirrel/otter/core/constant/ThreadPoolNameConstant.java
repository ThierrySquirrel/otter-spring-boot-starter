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
 * ClassName: ThreadPoolNameConstant
 * Description:
 * date: 2020/1/3 18:26
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum ThreadPoolNameConstant {
	/**
	 * Repair Retry
	 */
	REPAIR_RETRY("RepairRetry"),
	/**
	 * Repair
	 */
	REPAIR("Repair"),
	/**
	 * DataBaseRepair
	 */
	DATA_BASE_REPAIR("DataBaseRepair"),
	/**
	 * DataBaseRepairInit
	 */
	DATA_BASE_REPAIR_INIT("DataBaseRepairInit"),
	/**
	 * RemoveOldDataInit
	 */
	REMOVE_OLD_DATA_INIT("RemoveOldDataInit");
	private String value;

	ThreadPoolNameConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
