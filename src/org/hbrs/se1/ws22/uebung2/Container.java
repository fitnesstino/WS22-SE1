package org.hbrs.se1.ws22.uebung2;

import org.hbrs.se1.ws22.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private PersistenceStrategy<Member> persistenceStrategy;

    private List<Member> list=new ArrayList<>();
    private static final Container instanz = new Container();   //einzige Instanz des Objekts, die existieren kann

    private Container(){}   //Verhindert Instantiierung von außen

    public void addMember(Member member) throws ContainerException{
        if(member==null) throw new ContainerException();
        for(Member m : list){
            if (member.getID()==m.getID()){
                throw new ContainerException(m.getID().toString());
            }
        }
        list.add(member);
    }

    public Member getMember(int ID){
        for(Member member : list){
            if(member.getID()==ID){
                return member;
            }
        }
        return null;
    }

    public String deleteMember(Integer id){
        for(Member m : list){
            if(m.getID().equals(id)){
                list.remove(m);
                return id.toString();
            }
        }
        return null;
    }

    public int size(){
        return list.size();
    }

    //Gibt Instanz zurück (immer dieselbe)
    public static Container getInstanz(){
        return instanz;
    }

    public void store() throws PersistenceException{
        if(persistenceStrategy==null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es ist keine Strategie gesetzt!");
        }
        persistenceStrategy.openConnection();
        persistenceStrategy.save(list);
        persistenceStrategy.closeConnection();
    }

    public void load() throws PersistenceException{
        if(persistenceStrategy==null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es ist keine Strategie gesetzt!");
        }
        persistenceStrategy.openConnection();
        list=persistenceStrategy.load();
        persistenceStrategy.closeConnection();
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy){
        this.persistenceStrategy=persistenceStrategy;
    }

    public List<Member> getCurrentList(){
        return list;
    }
}

