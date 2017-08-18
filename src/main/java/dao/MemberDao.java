package dao;

import models.Member;

import java.util.List;


/**
 * Created by Guest on 8/18/17.
 */
public interface MemberDao {

    void add (Member member);

    List<Member> getAll();

    Member findById(int id);

    void update(int id, String memberName, int badge);
}
