package lab01.dao;

import java.util.List;

import lab01.model.MemberBean;

public interface MemberDao {

	boolean existsByMemberId(String memberId);

	void save(MemberBean mb);

	List<MemberBean> findAll();

	MemberBean findById(Integer id);

	void deleteById(Integer id);

	void update(MemberBean mb);

}