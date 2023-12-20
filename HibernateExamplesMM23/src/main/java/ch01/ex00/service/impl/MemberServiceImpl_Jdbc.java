package ch01.ex00.service.impl;

import java.util.List;

import ch01.ex00.dao.MemberDao;
import ch01.ex00.dao.impl.MemberDaoImpl_Jdbc;
import ch01.ex00.model.Member;
import ch01.ex00.service.MemberService;

public class MemberServiceImpl_Jdbc implements MemberService {
    MemberDao memberDao;
	public MemberServiceImpl_Jdbc() {
		memberDao = new MemberDaoImpl_Jdbc();
	}

	@Override
	public Member save(Member mem) {
		return memberDao.save(mem);
	}

	@Override
	public Member findById(Integer id) {
		return memberDao.findById(id);
	}

	@Override
	public boolean existsByUserId(String id) {
		return memberDao.existsByUserId(id);
	}

	@Override
	public void update(Member mem) {
		 memberDao.update(mem);
	}

	@Override
	public void delete(Integer id) {
		 memberDao.delete(id);
	}

		@Override
	public List<Member> findAll() {
		return memberDao.findAll();
	}

}
