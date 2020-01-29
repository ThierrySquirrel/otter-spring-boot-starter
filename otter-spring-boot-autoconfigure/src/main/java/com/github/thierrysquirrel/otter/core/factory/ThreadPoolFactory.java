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

import com.github.thierrysquirrel.otter.core.constant.ThreadPoolNameConstant;
import com.github.thierrysquirrel.otter.core.constant.ThreadPoolSizeConstant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * ClassName: ThreadPoolFactory
 * Description:
 * date: 2020/1/3 18:25
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ThreadPoolFactory {
	private ThreadPoolFactory() {
	}

	public static ScheduledThreadPoolExecutor createRepairRetryThreadPool() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat(ThreadPoolNameConstant.REPAIR_RETRY.getValue()).build();
		return new ScheduledThreadPoolExecutor(ThreadPoolSizeConstant.REPAIR_RETRY_CORE_POOL_SIZE.getValue(), threadFactory);
	}

	public static ThreadPoolExecutor createRepairThreadPool() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat(ThreadPoolNameConstant.REPAIR.getValue()).build();
		return new ThreadPoolExecutor(ThreadPoolSizeConstant.REPAIR_CORE_POOL_SIZE.getValue(),
				ThreadPoolSizeConstant.REPAIR_MAXI_MUM_POOL_SIZE.getValue(),
				ThreadPoolSizeConstant.REPAIR_KEEP_ALIVE_TIME.getValue(),
				TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(ThreadPoolSizeConstant.REPAIR_CAPACITY.getValue()),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}

	public static ThreadPoolExecutor createDataBaseRepairThreadPool() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat(ThreadPoolNameConstant.DATA_BASE_REPAIR.getValue()).build();
		return new ThreadPoolExecutor(ThreadPoolSizeConstant.DATA_BASE_REPAIR_CORE_POOL_SIZE.getValue(),
				ThreadPoolSizeConstant.DATA_BASE_REPAIR_MAXI_MUM_POOL_SIZE.getValue(),
				ThreadPoolSizeConstant.DATA_BASE_REPAIR_KEEP_ALIVE_TIME.getValue(),
				TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(ThreadPoolSizeConstant.DATA_BASE_REPAIR_CAPACITY.getValue()),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}

	public static ScheduledThreadPoolExecutor createDataBaseRepairInitThreadPool() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat(ThreadPoolNameConstant.DATA_BASE_REPAIR_INIT.getValue()).build();
		return new ScheduledThreadPoolExecutor(ThreadPoolSizeConstant.DATA_BASE_REPAIR_INIT_CORE_POOL_SIZE.getValue(), threadFactory);
	}

	public static ScheduledThreadPoolExecutor createRemoveOldDataInitThreadPool() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat(ThreadPoolNameConstant.REMOVE_OLD_DATA_INIT.getValue()).build();
		return new ScheduledThreadPoolExecutor(ThreadPoolSizeConstant.REMOVE_OLD_DATA_INIT_CORE_POOL_SIZE.getValue(), threadFactory);
	}

}
