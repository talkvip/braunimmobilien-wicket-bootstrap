package braunimmobilien.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import braunimmobilien.dao.MemberDao;
import braunimmobilien.model.Member;
import braunimmobilien.service.MemberManager;
@Service(value = "memberManager")
@Transactional(rollbackFor = Exception.class)
public class MemberManagerImpl implements MemberManager {

@Autowired
private MemberDao memberDao;

@Override
public List<Member> findAllByLastName(String lastName) {
return memberDao.findByLastName(lastName);
}

public void create(String firstName, String lastName) {
memberDao.create(firstName, lastName);
}

@Override
public Member findById(Long id) {
return memberDao.findById(id);
}

@Override
public List<Member> list(long offset, long count) {
return memberDao.list(offset, count);
}


@Override
public long count() {
return memberDao.count();
}

@Override
public List<Member> findAll() {
	return memberDao.findAll();
}

@Override
public Member findByProperty(String propertyName, Object propertyValue) {
// TODO Auto-generated method stub
return null;
}

@Override
public void remove(Member entity) {
// TODO Auto-generated method stub

}

@Override
public void persist(Member entity) {
memberDao.persist(entity);

}

}