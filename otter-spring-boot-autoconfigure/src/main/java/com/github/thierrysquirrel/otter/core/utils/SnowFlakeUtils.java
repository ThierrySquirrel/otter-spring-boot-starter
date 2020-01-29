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

package com.github.thierrysquirrel.otter.core.utils;


import com.github.thierrysquirrel.otter.core.constant.SnowFlakeConstant;
import com.github.thierrysquirrel.otter.core.domian.SnowFlakeDomain;
import com.github.thierrysquirrel.otter.core.factory.SnowFlakeDomainFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: SnowFlakeUtils
 * Description:
 * date: 2020/1/3 15:56
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class SnowFlakeUtils {
	private SnowFlakeDomain snowFlakeDomain;

	public SnowFlakeUtils() {
		this.snowFlakeDomain = SnowFlakeDomainFactory.createSnowFlakeDomain();
		log.debug("Current Time" + SnowFlakeConstant.START_STAMP);
	}

	public synchronized Long nextId()  {
		long currentStamp = System.currentTimeMillis();
		long lastStamp = snowFlakeDomain.getLastStamp();

		int sequence = snowFlakeDomain.getSequence();
		if (currentStamp == lastStamp) {

			sequence = (sequence + 1) & SnowFlakeConstant.MAX_SEQUENCE;

			if (sequence == 0L) {
				currentStamp = getNextMillis(lastStamp);
			}
		} else {
			sequence = 0;
		}
		snowFlakeDomain.setLastStamp(currentStamp);
		snowFlakeDomain.setSequence(sequence);

		return (currentStamp - SnowFlakeConstant.START_STAMP) << SnowFlakeConstant.TIMESTAMP_LEFT
				| snowFlakeDomain.getDataCenterId() << SnowFlakeConstant.DATA_CENTER_LEFT
				| snowFlakeDomain.getMachineId() << SnowFlakeConstant.MACHINE_LEFT
				| sequence;
	}

	private long getNextMillis(long lastStamp) {
		long millis = System.currentTimeMillis();
		while (millis <= lastStamp) {
			millis = System.currentTimeMillis();
		}
		return millis;
	}
}
