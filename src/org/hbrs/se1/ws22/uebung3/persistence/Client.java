package org.hbrs.se1.ws22.uebung3.persistence;

import org.hbrs.se1.ws22.uebung2.ConcreteMember;
import org.hbrs.se1.ws22.uebung2.Container;
import org.hbrs.se1.ws22.uebung2.ContainerException;

public class Client {
    public static void fill_then_dump(Container container){ //braucht eigentlich keinen Übergabewert, da immer nur eine Instanz von Container existiert
        for(int i=0;i<10;i++){
            try {
                container.addMember(new ConcreteMember((int) (Math.random() * 10 + i * 10)));
            } catch (ContainerException e) {
                throw new RuntimeException(e);
            }
        }
        new MemberView().dump(container.getCurrentList());
    }
}
