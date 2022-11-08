package org.hbrs.se1.ws22.uebung4;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EingabeDialog {

    private static final Predicate<String> doesntcontainnumerals=Pattern.compile("[0-9]").asPredicate().negate();
    public static void setTextcolor(String colorcode){
        System.out.print(colorcode);
    }
    public static void mainDialog(Container container){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen beim Mitarbeiterverwaltungssystem v0.1");
        while(true) {
            System.out.print(" > ");
            String[] strings = scanner.nextLine().split(" ");
            switch(strings[0]){
                case "help":
                    System.out.println("Verfügbare Befehle: exit, dump, store, load [force/merge], search [Suchstring], enter.");
                    break;
                case "exit":
                    System.out.println("Goodbye my Nigga, back to the Lobby!");
                    System.exit(0);
                    break;
                case "dump":
                    EmployeeView.create_table(container.getCurrentList(),(strings.length>2&&strings[1].equals("abteilung"))?strings[2]:null);
                    break;
                case "store":
                    try {
                        container.store();
                        System.out.println("Speichern erfolgreich.");
                    }catch(PersistenceException e){
                        System.out.println("Es wurde ein Fehler gefangen!");
                        System.out.println("Typ des Fehlers: "+e.getExceptionType());
                        System.out.println("Fehlertext: "+e.getMessage());
                    }
                    break;
                case "load":
                    if(strings.length==1){
                        System.out.println("Missing Parameter!");
                    }else{
                    switch(strings[1]){
                        case "merge":
                            try{
                                container.load(false);
                                System.out.println("Laden erfolgreich!");
                            }catch(PersistenceException e){
                                System.out.println("Es wurde ein Fehler gefangen!");
                                System.out.println("Typ des Fehlers: "+e.getExceptionType());
                                System.out.println("Fehlertext: "+e.getMessage());
                            }catch(ContainerException e){
                                System.out.println("Es wurde ein Fehler gefangen!");
                                System.out.println("Fehlertext: "+e.getMessage());
                            }
                            break;
                        case "force":
                            try{
                                container.load(true);
                                System.out.println("Laden erfolgreich!");
                            }catch(PersistenceException e){
                                System.out.println("Es wurde ein Fehler gefangen!");
                                System.out.println("Typ des Fehlers: "+e.getExceptionType());
                                System.out.println("Fehlertext: "+e.getMessage());
                            }catch(ContainerException e){
                                System.out.println("Es wurde ein Fehler gefangen!");
                                System.out.println("Fehlertext: "+e.getMessage());
                            }
                            break;
                        default:
                            System.out.println("Wrong Parameter");
                    }}
                    break;
                case "search":
                    if(strings.length==1){
                        System.out.println("Kein Suchargument gegeben!");
                        break;
                    }
                    EmployeeView.search_table(container.getCurrentList(),strings[1]);
                    break;
                case "enter":
                    Employee employee=new Employee();
                    while(true) {
                        System.out.print("PID?");
                        try{
                            employee.setPid(scanner.nextInt());
                            break;
                        }catch(InputMismatchException e){}
                    }
                    scanner.nextLine();
                    while(true) {
                        System.out.print("Vorname?");
                        String string = scanner.nextLine();
                        if(doesntcontainnumerals.test(string)&&string.length()>1){
                            employee.setVorname(string);
                            break;
                        }
                    }
                    while(true) {
                        System.out.print("Name?");
                        String string = scanner.nextLine();
                        if(doesntcontainnumerals.test(string)&&string.length()>1){
                            employee.setName(string);
                            break;
                        }
                    }
                    while(true) {
                        System.out.print("Rolle im Unternehmen?");
                        String string = scanner.nextLine();
                        if(string.length()>0){
                            employee.setRolle(string);
                            break;
                        }
                    }
                    System.out.print("Abteilung?");
                    employee.setAbteilung(scanner.nextLine());
                    while (true){
                        System.out.print("Expertisen?");
                        String[] strings1= scanner.nextLine().split(" ");
                        if(strings1.length==1) break;
                        try{
                            for(int i=0; i<strings1.length;i+=2){
                                employee.expertisen.add(strings1[i], Integer.parseInt(strings1[i+1]));
                            }
                            break;
                        }catch(InputMismatchException e){
                            employee.expertisen.reset();
                        }catch(ArrayIndexOutOfBoundsException e){
                            employee.expertisen.reset();
                        }
                    }
                    try {
                        container.addEmployee(employee);
                    }catch(ContainerException e){
                        System.out.println("Mitarbeiter mit der eingegebenen PID existiert schon!");
                    }
                    break;
                default:
                    System.out.println("Ungültige Anweisung eingegeben!");
            }
        }
    }
}
