package org.hbrs.se1.ws22.uebung4;

public class Main {
    public static void main(String[] args)throws ContainerException{
        Container.getInstanz().setPersistenceStrategy(new PersistenceStrategyStream<Employee>());
        EingabeDialog.setTextcolor("\u001B[92m");
        EingabeDialog.mainDialog(Container.getInstanz());
    }
}
