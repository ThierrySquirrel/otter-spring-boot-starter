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
package com.github.thierrysquirrel.otter.init.core.factory;

import com.github.thierrysquirrel.otter.annotation.Repair;
import com.github.thierrysquirrel.otter.core.container.RepairContainer;
import com.github.thierrysquirrel.otter.core.domain.RepairDomain;
import com.github.thierrysquirrel.otter.core.domain.builder.RepairDomainBuilder;
import com.github.thierrysquirrel.otter.core.exception.OtterException;
import com.google.common.primitives.Ints;

import java.lang.reflect.Method;

/**
 * ClassName: RepairInitFactory
 * Description:
 * date: 2020/8/28 20:01
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class RepairInitFactory {
    private RepairInitFactory() {
    }

    public static Method getRepairFallbackMethod(Object repairFallbackBean, String fallbackMethodName, Class<?>[] fallbackMethodParamTypes) throws OtterException {
        try {
            return repairFallbackBean.getClass ().getMethod (fallbackMethodName, fallbackMethodParamTypes);
        } catch (NoSuchMethodException e) {
            throw new OtterException ("Get Repair Fallback Method Error", e);
        }

    }

    public static void addRepairDomain(Object otterBean, Method repairMethod, Repair repair) {
        int[] repairInterval = repair.repairInterval ();
        RepairDomain repairDomain = RepairDomainBuilder.builderRepairDomain (Ints.asList (repairInterval), otterBean, repairMethod);
        RepairContainer.putRepair (repairMethod.toString (), repairDomain);
    }

}
