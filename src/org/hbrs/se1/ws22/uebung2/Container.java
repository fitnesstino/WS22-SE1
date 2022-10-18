package org.hbrs.se1.ws22.uebung2;

import java.util.ArrayList;
import java.util.Objects;

public class Container {

    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member) throws ContainerException{
        //Prüfe, ob Element schon vorhanden
        for (Member memberElement : members) {
            if (Objects.equals(memberElement.getID(), member.getID())) {
                throw new ContainerException(member.getID());
            }
        }
        members.add(member);
    }

    public String deleteMember(Integer id) {
        //Durchsuche Datenstruktur nach Member
        for (Member memberElement : members) {
            if (Objects.equals(memberElement.getID(), id)) {
                members.remove(memberElement);
                return "" + memberElement.getID();
            }
        }
        return null;
    }

    public void dump() {
        for (Member memberElement : members) {
            System.out.print(memberElement.toString() + " ");
        }
    }

    public int size() {
        return members.size();
    }

}
