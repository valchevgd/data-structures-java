import models.Employee;

public class SimpleHastable {

    private StoredEmployee[] hashtable;

    public SimpleHastable() {
        hashtable = new StoredEmployee[10];
    }

    public void put(String key, Employee employee) {
        int hashedKey = hashKey(key);

        if (occupied(hashedKey)) {
            int stopIndex = hashedKey;

            if (hashedKey == hashtable.length) {
                hashedKey = 0;
            } else {
                hashedKey++;
            }

            while (occupied(hashedKey) && hashedKey != stopIndex) {
                hashedKey = (hashedKey + 1) % hashtable.length;
            }
        }

        if (occupied(hashedKey)) {
            System.out.println("Sorry");
        } else {
            hashtable[hashedKey] = new StoredEmployee(key, employee);
        }
    }

    public Employee get(String key) {
        int hashedKey = findKey(key);

        if (hashedKey == -1) {
            return null;
        }

        return hashtable[hashedKey].getEmployee();
    }

    public Employee remove(String key) {
        int hashedKey = findKey(key);

        if (hashedKey == -1) {
            return null;
        }

        Employee employee = hashtable[hashedKey].getEmployee();
        hashtable[hashedKey] = null;

        StoredEmployee[] oldHashtable = hashtable;
        hashtable = new StoredEmployee[hashtable.length];

        for (int i = 0; i < hashtable.length; i++) {
            if (oldHashtable[i] != null) {
                put(oldHashtable[i].getKey(), oldHashtable[i].getEmployee());
            }
        }


        return employee;
    }

    public void printHashtable() {
        for (StoredEmployee storedEmployee : hashtable) {
            System.out.println(storedEmployee.getEmployee());
        }
    }

    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }

    private boolean occupied(int index) {
        return hashtable[index] != null;
    }

    private int findKey(String key) {
        int hashedKey = hashKey(key);

        if (hashtable[hashedKey] != null && hashtable[hashedKey].getKey().equals(key)) {
            return hashedKey;
        }


        int stopIndex = hashedKey;

        if (hashedKey == hashtable.length) {
            hashedKey = 0;
        } else {
            hashedKey++;
        }

        while (hashedKey != stopIndex &&
                hashtable[hashedKey] != null &&
                !hashtable[hashedKey].getKey().equals(key)) {
            hashedKey = (hashedKey + 1) % hashtable.length;
        }

        if (hashtable[hashedKey] != null &&
                hashtable[hashedKey].getKey().equals(key)) {
            return hashedKey;
        }

        return -1;
    }
}
