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

package com.github.thierrysquirrel.otter.repository;

import com.github.thierrysquirrel.otter.repository.entity.OtterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * ClassName: OtterRepository
 * Description:
 * date: 2020/1/3 15:50
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Repository
public interface OtterRepository extends JpaRepository<OtterEntity, Long> {
	/**
	 * findByParameter
	 *
	 * @param parameter parameter
	 * @return OtterEntity
	 */
	OtterEntity findByParameter(byte[] parameter);

	/**
	 * updateIsDeleted
	 *
	 * @param id id
	 */
	@Modifying
	@Query("update OtterEntity otter set otter.isDeleted = 1 where otter.id = ?1")
	@Transactional(rollbackFor = Exception.class)
	void updateIsDeleted(Long id);

	/**
	 * findAllByIsDeleted
	 *
	 * @param isDeleted isDeleted
	 * @param pageable  pageable
	 * @return OtterEntity
	 */
	Page<OtterEntity> findAllByIsDeleted(byte isDeleted, Pageable pageable);

	/**
	 * findAllByIsDeletedAndGmtModifiedBefore
	 *
	 * @param isDeleted   isDeleted
	 * @param gmtModified gmtModified
	 * @param pageable    pageable
	 * @return OtterEntity
	 */
	Page<OtterEntity> findAllByIsDeletedAndGmtModifiedBefore(byte isDeleted, Date gmtModified, Pageable pageable);

}
