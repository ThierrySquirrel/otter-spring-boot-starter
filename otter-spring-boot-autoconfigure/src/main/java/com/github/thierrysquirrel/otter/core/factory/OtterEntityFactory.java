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

import com.github.thierrysquirrel.otter.core.utils.SerializerUtils;
import com.github.thierrysquirrel.otter.repository.entity.OtterEntity;
import com.github.thierrysquirrel.otter.repository.entity.OtterEntityParameter;

/**
 * ClassName: OtterEntityFactory
 * Description:
 * date: 2020/1/3 16:56
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class OtterEntityFactory {
	private OtterEntityFactory() {
	}

	public static OtterEntity createOtterEntity(Long id, String methodName, Object[] parameter) {
		OtterEntityParameter otterEntityParameter = new OtterEntityParameter(methodName, parameter);
		byte[] serialize = SerializerUtils.serialize(otterEntityParameter);

		OtterEntity otterEntity = new OtterEntity();
		otterEntity.setId(id);
		otterEntity.setParameter(serialize);
		return otterEntity;
	}
}
