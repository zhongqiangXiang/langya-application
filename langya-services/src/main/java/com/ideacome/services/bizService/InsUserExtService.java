package com.ideacome.services.bizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideacome.common.db.domain.InsUserExt;
import com.ideacome.common.db.domain.InsUserExtExample;
import com.ideacome.common.db.persistence.InsUserExtMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InsUserExtService {
	@Autowired
	private InsUserExtMapper insUserExtMapper;
	
	/**
	 * 示例
	 */
	public List<InsUserExt> selectInsUserExtSelective(Long userId){
		log.info("InsUserExtService.selectInsUserExtSelective 参数：userId->{}",userId);
		InsUserExtExample example = new InsUserExtExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return insUserExtMapper.selectByExample(example);
	}
}
