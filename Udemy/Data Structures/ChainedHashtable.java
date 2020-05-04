import models.Employee;

import java.util.LinkedList;

public class ChainedHashtable {

    private LinkedList<StoredEmployee>[] hashtable;

    public ChainedHashtable() {
        hashtable = new LinkedList[10];
        for (int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new LinkedList<StoredEmployee>();
        }
    }

    public void put(String key, Employee employee) {

        int hashedKey = hashKey(key);

        hashtable[hashedKey].add(new StoredEmployee(key, employee));
    }

    public Employee get(String key) {
        int hashedKey = hashKey(key);

        for (StoredEmployee storedEmployee : hashtable[hashedKey]) {
            if (storedEmployee.getKey().equals(key)) {
                return storedEmployee.getEmployee();
            }
        }

        return null;
    }

    public Employee remove(String key) {

        int hashedKey = hashKey(key);

        int index = 0;

        for (StoredEmployee storedEmployee : hashtable[hashedKey]) {
            if (storedEmployee.getKey().equals(key)) {
                Employee employee = storedEmployee.getEmployee();
                hashtable[hashedKey].remove(index);
                return employee;
            }
            index++;
        }

        return null;
    }

    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }
}
