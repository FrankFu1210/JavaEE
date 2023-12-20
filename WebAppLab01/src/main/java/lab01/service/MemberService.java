package lab01.service;

import java.util.List;

import lab01.model.MemberBean;

public interface MemberService {

	boolean existsByMemberId(String memberId);

	void save(MemberBean mb);

	List<MemberBean> findAll();

	MemberBean findById(Integer id);

	void deleteById(Integer id);

	void update(MemberBean mb);

}