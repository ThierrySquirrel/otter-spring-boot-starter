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

import com.github.thierrysquirrel.otter.core.factory.ThreadPoolFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ThreadPoolFactoryConstant
 * Description:
 * date: 2020/1/3 17:16
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class ThreadPoolFactoryConstant {
	public static final ScheduledThreadPoolExecutor REPAIR_RETRY_THREAD_POOL = ThreadPoolFactory.createRepairRetryThreadPool();
	public static final ThreadPoolExecutor REPAIR_THREAD_POOL = ThreadPoolFactory.createRepairThreadPool();
	public static final ThreadPoolExecutor DATA_BASE_REPAIR_THREAD_POOL = ThreadPoolFactory.createDataBaseRepairThreadPool();
	public static final ScheduledThreadPoolExecutor DATA_BASE_REPAIR_INIT_THREAD_POOL = ThreadPoolFactory.createDataBaseRepairInitThreadPool();
	public static final ScheduledThreadPoolExecutor REMOVE_OLD_DATA_INIT_THREAD_POOL = ThreadPoolFactory.createRemoveOldDataInitThreadPool();

	private ThreadPoolFactoryConstant() {
	}
}
