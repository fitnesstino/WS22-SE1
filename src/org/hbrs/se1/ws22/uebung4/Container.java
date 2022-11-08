package org.hbrs.se1.ws22.uebung4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Container {

    private PersistenceStrategy<Employee> persistenceStrategy;

    private List<Employee> list=new ArrayList<>();
    private static final Container instanz = new Container();   //einzige Instanz des Objekts, die existieren kann

    private Container(){}   //Verhindert Instantiierung von außen

    private void sortlist(){
        Collections.sort(list);
    }

    public void addEmployee(Employee employee) throws ContainerException {
        if(employee==null) throw new ContainerException();
        for (Employee employee1:list){
            if(employee1.getPid().equals(employee.getPid())){
                throw new ContainerException(employee.getPid().toString());
            }
        }
        list.add(employee);
        sortlist();
    }

    public Employee getEmployee(int ID){
        for(Employee member : list){
            if(member.getPid()==ID){
                return member;
            }
        }
        return null;
    }

    public String deleteEmployee(Integer id){
        for(Employee m : list){
            if(m.getPid().equals(id)){
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
        try {
            persistenceStrategy.openConnection();
            persistenceStrategy.save(list);
            persistenceStrategy.closeConnection();
        }catch(UnsupportedOperationException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,e.getMessage());
        }
    }

    public void load(boolean overwrite) throws PersistenceException, ContainerException{
        if(persistenceStrategy==null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es ist keine Strategie gesetzt!");
        }
        List<Employee> temp_list;
        try {
            persistenceStrategy.openConnection();
            temp_list = persistenceStrategy.load();
            persistenceStrategy.closeConnection();
        }catch(UnsupportedOperationException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,e.getMessage());
        }
        if(overwrite){
            list=temp_list;
        }else {
            for(Employee employee:temp_list){
                this.addEmployee(employee);
            }
            sortlist();
        }
    }

    public void setPersistenceStrategy(PersistenceStrategy<Employee> persistenceStrategy){
        this.persistenceStrategy=persistenceStrategy;
    }

    public List<Employee> getCurrentList(){
        return list;
    }
}

