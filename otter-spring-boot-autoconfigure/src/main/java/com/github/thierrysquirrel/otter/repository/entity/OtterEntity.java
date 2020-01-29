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

package com.github.thierrysquirrel.otter.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * ClassName: OtterEntity
 * Description:
 * date: 2020/1/3 15:51
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
@Entity
@Table(indexes = {@Index(name = " idx_parameter", columnList = "parameter")})
public class OtterEntity {
	@Id
	@Column(columnDefinition = "bigint(16) NOT NULL")
	private Long id;

	@Column(columnDefinition = "varchar(512) NOT NULL")
	private byte[] parameter;

	@Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0")
	private byte isDeleted;

	@Column(columnDefinition = "timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	@Column(columnDefinition = "timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0)")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
}
