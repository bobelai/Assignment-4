
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure {
    private LinkedList<CourseDBElement>[] hashTable;
    private int tableSize;

    public CourseDBStructure(int n) {
        double loadFactor = 1.5;
        int initialSize = (int) (n / loadFactor);
        tableSize = findNext4kPlus3Prime(initialSize);
        hashTable = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    public CourseDBStructure(String test, int size) {
        tableSize = size;
        hashTable = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    private int findNext4kPlus3Prime(int num) {
        int k = (num - 3) / 4;
        if ((num - 3) % 4 != 0) {
            k += 1;
        }
        int candidate = 4 * k + 3;
        while (!isPrime(candidate)) {
            k += 1;
            candidate = 4 * k + 3;
        }
        return candidate;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public void add(CourseDBElement element) {
        int index = Math.abs(element.getCRN() % tableSize);
        LinkedList<CourseDBElement> bucket = hashTable[index];
        for (CourseDBElement existingElement : bucket) {
            if (existingElement.getCRN() == element.getCRN()) {
                existingElement.setCourseID(element.getCourseID());
                existingElement.setCredits(element.getCredits());
                existingElement.setRoomNumber(element.getRoomNumber());
                existingElement.setInstructor(element.getInstructor());
                return;
            }
        }
        bucket.add(element);
    }

    public CourseDBElement get(int crn) throws IOException {
        int index = Math.abs(crn % tableSize);
        LinkedList<CourseDBElement> bucket = hashTable[index];
        for (CourseDBElement element : bucket) {
            if (element.getCRN() == crn) {
                return element;
            }
        }
        throw new IOException("Course not found.");
    }

    public ArrayList<String> showAll() {
        ArrayList<String> courses = new ArrayList<>();
        for (LinkedList<CourseDBElement> bucket : hashTable) {
            for (CourseDBElement element : bucket) {
                courses.add(element.toString());
            }
        }
        return courses;
    }

    public int getTableSize() {
        return tableSize;
    }
}
