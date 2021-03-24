package business.entities.iterators;

import java.util.Iterator;
import business.entities.Member;
import business.facade.Result;

/**
 * Safe Iterator for Member lists
 * @author Michael Olson
 *
 */
public class SafeMemberIterator extends SafeIterator<Member> {

	public SafeMemberIterator(Iterator<Member> iterator) {
		super(iterator);
	}

	@Override
	public void copy(Result result, Object object) {
        Member member = (Member) object;
        result.setMember(member);
    }
}
