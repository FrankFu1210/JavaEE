package lab01.service.impl;

import java.util.List;

import lab01.dao.MemberDao;
import lab01.dao.impl.MemberJdbcDaoImpl;
import lab01.model.MemberBean;
import lab01.service.MemberService;

public class MemberServiceImpl implements MemberService {
	
	MemberDao memberDao = null;
	
	public MemberServiceImpl() {
		memberDao = new MemberJdbcDaoImpl();
	}	

	public MemberBean findById(Integer id) {
		return memberDao.findById(id);
	}
	
	public List<MemberBean> findAll() {
		return memberDao.findAll();
	}

	public void save(MemberBean bean) {
		 memberDao.save(bean);
	}


	public void deleteById(Integer id) {
		memberDao.deleteById(id);
	}

	@Override
	public boolean existsByMemberId(String memberId) {
		return memberDao.existsByMemberId(memberId);
	}

	@Override
	public void update(MemberBean memberBean) {
		memberDao.update(memberBean);
		
	}
}