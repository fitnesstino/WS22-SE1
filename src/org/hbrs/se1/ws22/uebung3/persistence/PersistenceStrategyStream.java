package org.hbrs.se1.ws22.uebung3.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of Use Case, in which the objects are stored
    private String location = "objects.ser";


    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        /*
            ObjectInputStream is looking for Metadata in the Use Case as soon as it is created, therefore it cannot
            be created together with ObjectOutputStream as ObjectOutputStream is needed to write the metadata to
            the Use Case. This means that either a previously created Use Case or different openConnection methods for
            read and write are required.
         */
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {

    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException  {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream=new FileOutputStream(location);
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound, e.getMessage());
        }

        try {
            objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(member);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.IOError,e.getMessage());
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        Object obj;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        try {
            fileInputStream=new FileInputStream(location);
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound,e.getMessage());
        }

        try {
            objectInputStream=new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.IOError,e.getMessage());
        }

        try {
            obj = objectInputStream.readObject();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.IOError, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ClassError, "Read Object is not of known Class!");
        }

        try {
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.IOError, e.getMessage());
        }

        if (obj instanceof List<?>) {
            return (List<E>) obj;
        }
        throw new PersistenceException(PersistenceException.ExceptionType.ClassError, "Read Object is not of known Class!");
    }
}
