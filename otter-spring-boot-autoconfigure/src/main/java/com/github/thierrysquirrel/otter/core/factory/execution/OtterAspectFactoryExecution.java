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
package com.github.thierrysquirrel.otter.core.factory.execution;

import com.github.thierrysquirrel.otter.core.container.RepairContainer;
import com.github.thierrysquirrel.otter.core.exception.OtterException;
import com.github.thierrysquirrel.otter.core.factory.OtterAspectFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ClassName: OtterAspectFactoryExecution
 * Description:
 * date: 2020/8/28 20:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class OtterAspectFactoryExecution {
    private OtterAspectFactoryExecution() {
    }

    public static Object execution(ProceedingJoinPoint point, String methodString) throws OtterException {
        if (OtterAspectFactory.isIntercept ()) {
            return OtterAspectFactory.repairInterval (point, RepairContainer.getRepair (methodString));
        }
        return OtterAspectFactory.release (point);
    }
}
