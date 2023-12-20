package ch01.ex00.dao;

import java.util.List;

import ch01.ex00.model.Member;

public interface MemberDao {
	public Member save(Member mem);

	public Member findById(Integer id);

	public boolean existsByUserId(String id);

	public void update(Member mem);

	public void delete(Integer id);

	public List<Member> findAll();

}
