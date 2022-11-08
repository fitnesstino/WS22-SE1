package org.hbrs.se1.ws22.uebung4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable, Comparable {

    private String vorname;
    private String name;
    private Integer pid;
    private String rolle;
    private String abteilung;

    public Expertisen expertisen=new Expertisen();

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Employee){
            return this.pid-((Employee) o).pid;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Employee){
            return ((Employee) obj).pid==this.pid;
        }
        return super.equals(obj);
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public class Expertisen implements Serializable{


        class Expertise implements Serializable{

            private String[] Expertise_Level={
                    null,
                    "Beginner",
                    "Experte",
                    "Top-Performer"
            };
            public String Expertise;
            public int Level;
            public Expertise(String expertise, int level){
                this.Expertise=expertise;
                this.Level=level;
            }
            public String toString(){
                return Expertise+": "+Expertise_Level[Level];
            }
        }
        private static final int initialCapacity=3;
        private ArrayList<Expertise> expertisen=new ArrayList<>(initialCapacity);
        public List<Expertise> get() {
            return expertisen;
        }
        public void add(String expertise,int level){
            add(new Expertise(expertise,level));
        }
        public void add(Expertise expertise){
            expertisen.add(expertise);
        }
        public void reset(){
            expertisen =new ArrayList<>(initialCapacity);
        }
        public boolean contains(String string){
            for(Expertise expertise:expertisen){
                if (expertise.Expertise.equals(string)){
                    return true;
                }
            }
            return false;
        }
        public String toString(){
            String ret="";
            for(Expertise expertise:expertisen){
                ret+=expertise.toString()+" ";
            }
            return ret;
        }
    }
}

