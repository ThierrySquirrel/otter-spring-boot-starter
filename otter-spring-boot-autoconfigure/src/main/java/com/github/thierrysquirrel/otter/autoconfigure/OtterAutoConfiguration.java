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

import com.github.thierrysquirrel.otter.aspect.OtterAspect;
import com.github.thierrysquirrel.otter.init.RepairInit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: OtterAutoConfiguration
 * Description:
 * date: 2020/8/28 19:45
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Configuration
public class OtterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(RepairInit.class)
    public RepairInit repairInit() {
        return new RepairInit ();
    }

    @Bean
    @ConditionalOnMissingBean(OtterAspect.class)
    public OtterAspect otterAspect() {
        return new OtterAspect ();
    }
}
