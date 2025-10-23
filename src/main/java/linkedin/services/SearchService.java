package linkedin.services;

import linkedin.entities.Member;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private final Collection<Member> members;

    public SearchService(Collection<Member> members) {
        this.members = members;
    }
    public List<Member> searchByName(String name) {
        return members.stream()
                .filter(member -> member.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
