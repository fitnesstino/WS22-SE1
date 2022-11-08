package org.hbrs.se1.ws22.uebung4;

import java.util.List;

public class EmployeeView {
    public static void create_table(List<Employee> list, String abteilung){
        if(abteilung==null){
            list.forEach(employee -> {
                System.out.println("Mitarbeiter Nr."+employee.getPid()+": "+employee.getVorname()+" "+employee.getName()+
                        "; Abteilung, Rolle: "+employee.getAbteilung()+", "+employee.getRolle()+"; Expertise(n): "+employee.expertisen.toString());
            });
        }else{
            list.stream().filter(employee -> employee.getAbteilung().equals(abteilung)).forEach(employee ->
                    System.out.println("Mitarbeiter Nr."+employee.getPid()+": "+employee.getVorname()+" "+employee.getName()+
                    "; Rolle: "+employee.getRolle()+"; Expertise(n): "+employee.expertisen.toString()));
        }
    }

    public static void search_table(List<Employee> list, String expertise){
        list.stream().filter(employee -> employee.expertisen.contains(expertise)).forEach(employee ->
            System.out.println("Mitarbeiter Nr."+employee.getPid()+": "+employee.getVorname()+" "+employee.getName()+
                    "; Abteilung, Rolle: "+employee.getAbteilung()+", "+employee.getRolle()+
                    "; Expertise(n): "+employee.expertisen.toString()));
    }
}
