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

import com.github.thierrysquirrel.otter.core.domian.MethodDomain;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * ClassName: OtterContainerFactory
 * Description:
 * date: 2020/1/3 16:36
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class OtterContainerFactory {
	private static Map<String, MethodDomain> otterContainer = Maps.newConcurrentMap();

	private OtterContainerFactory() {
	}

	public static void addMethodDomain(Method method, Object object) {
		MethodDomain methodDomain = new MethodDomain(method, object);
		otterContainer.put(method.getName(), methodDomain);
	}

	public static MethodDomain getMethodDomain(String methodName) {
		return otterContainer.get(methodName);
	}
}
