/**
 * Copyright 2020 the original author or authors.
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

package com.github.thierrysquirrel.otter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: OtterProperties
 * Description:
 * date: 2020/1/3 15:34
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
@ConfigurationProperties(prefix = OtterProperties.OTTER_PREFIX)
public class OtterProperties {
	public static final String OTTER_PREFIX = "otter";
	/**
	 * Interval Between Attempts To Repair Again After Repair Failure
	 * Time Unit, Second
	 * <p>
	 * 修复失败后,尝试再次修复的间隔时间
	 * 时间单位,秒
	 */
	private Integer[] repairInterval = {2, 4, 16, 32};
	/**
	 * Multiple Repair Failures, Data From The Database, Retry To Join The Repair Queue
	 * Time Unit, Second
	 * <p>
	 * 多次修复失败,来自数据库的数据,重新尝试加入修复队列
	 * 时间单位,秒
	 */
	private Integer dataBaseRepairTime = 64;
	/**
	 * Number Of Database Queries
	 * <p>
	 * 数据库查询数量
	 */
	private Integer dataBaseRepairNumber = 256;
	/**
	 * Interval To Check If The Database Has Expired
	 * Time Unit, Second
	 * <p>
	 * 检查数据库是否过期的间隔时间
	 * 时间单位,秒
	 */
	private Integer removeOldDataInterval = 30;
	/**
	 * Data Retention Period
	 * Time Unit, Day
	 * <p>
	 * 数据保持期
	 */
	private Integer removeOldDataTime = 30;
	/**
	 * Delete Quantity
	 * <p>
	 * 删除数量
	 */
	private Integer removeOldDataNumber = 256;
}
