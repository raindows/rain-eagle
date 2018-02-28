package org.rain.eagle.rpc.server.service;

import org.rain.eagle.rpc.server.entity.EagleUser;

public interface EagleUserService {

	int insert(EagleUser eagleUser);

	int updateByPrimaryKeySelective(EagleUser eagleUser);

	EagleUser selectByPrimaryKey(Long id);

}
