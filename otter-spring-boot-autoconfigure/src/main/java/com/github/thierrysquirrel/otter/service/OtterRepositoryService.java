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

package com.github.thierrysquirrel.otter.service;

import com.github.thierrysquirrel.otter.core.constant.OtterRepositoryServiceConstant;
import com.github.thierrysquirrel.otter.core.factory.OtterEntityFactory;
import com.github.thierrysquirrel.otter.core.factory.OtterEntityOutputFactory;
import com.github.thierrysquirrel.otter.repository.OtterRepository;
import com.github.thierrysquirrel.otter.repository.entity.OtterEntity;
import com.github.thierrysquirrel.otter.repository.entity.OtterEntityOutput;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: OtterRepositoryService
 * Description:
 * date: 2020/1/3 16:42
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class OtterRepositoryService {
	private OtterRepository otterRepository;

	public OtterRepositoryService(OtterRepository otterRepository) {
		this.otterRepository = otterRepository;
	}

	public boolean saveOtterEntity(Long id, String methodName, Object[] parameter) {
		OtterEntity otterEntity = OtterEntityFactory.createOtterEntity(id, methodName, parameter);
		OtterEntity byParameter = otterRepository.findByParameter(otterEntity.getParameter());
		if (null == byParameter) {
			otterRepository.save(otterEntity);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public void modifyOtterEntity(Long id) {
		otterRepository.updateIsDeleted(id);
	}

	public List<OtterEntityOutput> searchOtterEntityOutputList(Integer dataBaseRepairNumber) {
		return otterRepository.findAllByIsDeleted(OtterRepositoryServiceConstant.NOT_REPAIRED,
				PageRequest.of(OtterRepositoryServiceConstant.PAGE_INDEX, dataBaseRepairNumber))
				.toList()
				.stream()
				.map(OtterEntityOutputFactory::createOtterEntityOutput)
				.collect(Collectors.toList());
	}

	public void removeOldData(Integer removeOldDataTime, Integer removeOldDataNumber) {
		LocalDate localDate = LocalDate.now();
		LocalDate removeDate = localDate.minusDays(removeOldDataTime);
		ZonedDateTime zonedDateTime = removeDate.atStartOfDay(ZoneId.systemDefault());
		Page<OtterEntity> oldData = otterRepository.findAllByIsDeletedAndGmtModifiedBefore(OtterRepositoryServiceConstant.REPAIRED,
				Date.from(zonedDateTime.toInstant()),
				PageRequest.of(OtterRepositoryServiceConstant.PAGE_INDEX, removeOldDataNumber));
		otterRepository.deleteInBatch(oldData);
	}
}
