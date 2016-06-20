package braunimmobilien.dao;

import java.util.List;
import braunimmobilien.model.Member;

public interface MemberDao extends DAO<Member> {

List<Member> findByLastName(String lastName);

void create(String firstName, String lastName);
}