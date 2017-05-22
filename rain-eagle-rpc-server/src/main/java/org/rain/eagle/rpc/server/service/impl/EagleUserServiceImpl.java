package org.rain.eagle.rpc.server.service.impl;

import org.rain.eagle.rpc.server.entity.EagleUser;
import org.rain.eagle.rpc.server.repository.EagleUserRepository;
import org.rain.eagle.rpc.server.service.EagleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EagleUserServiceImpl implements EagleUserService {

	@Autowired
	private EagleUserRepository eagleUserRepository;

	@Transactional(value = "eagleTxManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@Override
	public int insert(EagleUser eagleUser) {
		int insert = eagleUserRepository.insert(eagleUser);
		if (insert == 1) {
			throw new RuntimeException();
		}
		return insert;
	}

	/*@Transactional(value = "eagleTxManager", propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = ClassCastException.class)
	@Override
	public int updateByPrimaryKeySelective(EagleUser eagleUser) {
		int update = eagleUserRepository.updateByPrimaryKeySelective(eagleUser);
		if (update == 1) {
			throw new ClassCastException();
		}
		return update;
	}*/
	
	//@Transactional(value = "eagleTxManager", propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public int updateByPrimaryKeySelective(EagleUser eagleUser) {
		int update = this.update(eagleUser);//this調用非代理調用 而是被代理對象調用
		return update;
	}

	@Transactional(value = "eagleTxManager", propagation = Propagation.REQUIRED, readOnly = false)
	public int update(EagleUser eagleUser){
		int update = eagleUserRepository.updateByPrimaryKeySelective(eagleUser);
		if (update == 1) {
			throw new RuntimeException("update(EagleUser eagleUser)执行更新异常");
		}
		return update;
	}

	@Transactional(value = "eagleTxManager", propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
	@Override
	public EagleUser selectByPrimaryKey(Long id) {
		return eagleUserRepository.selectByPrimaryKey(id);
	}

}
