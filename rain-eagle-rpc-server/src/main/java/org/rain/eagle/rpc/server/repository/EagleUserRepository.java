package org.rain.eagle.rpc.server.repository;

import org.rain.eagle.rpc.server.entity.EagleUser;
import org.springframework.stereotype.Repository;

@Repository
public interface EagleUserRepository {

	int insert(EagleUser eagleUser);

	int updateByPrimaryKeySelective(EagleUser eagleUser);

	EagleUser selectByPrimaryKey(Long id);
}
