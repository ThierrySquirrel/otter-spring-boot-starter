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


import com.github.thierrysquirrel.otter.core.constant.SnowFlakeConstant;
import com.github.thierrysquirrel.otter.core.domian.SnowFlakeDomain;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SnowFlakeDomainFactory
 * Description:
 * date: 2020/1/3 15:55
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SnowFlakeDomainFactory {
    private SnowFlakeDomainFactory() {
    }

    public static SnowFlakeDomain createSnowFlakeDomain() {
        SnowFlakeDomain snowFlakeDomain = new SnowFlakeDomain ();
        long thisTime = System.currentTimeMillis ();
        thisTime = TimeUnit.MILLISECONDS.toSeconds (thisTime);
        snowFlakeDomain.setThisTime (thisTime);
        snowFlakeDomain.setDataCenterId (ThreadLocalRandom.current ().nextInt (SnowFlakeConstant.MAX_DATA_CENTER_NUM));
        snowFlakeDomain.setMachineId (ThreadLocalRandom.current ().nextInt (SnowFlakeConstant.MAX_MACHINE_NUM));
        return snowFlakeDomain;
    }
}
