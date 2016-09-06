package braunimmobilien.service;

import java.util.List;

import braunimmobilien.model.Member;
import braunimmobilien.service.EntityManager;

public interface MemberManager extends EntityManager<Member>{

List<Member> findAllByLastName(String lastName);

void create(String firstname, String lastname);
}