
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
    private CourseDBStructure cds;

    public CourseDBManager() {
        this.cds = new CourseDBStructure(100);
    }

    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
        cds.add(element);
    }

    @Override
    public CourseDBElement get(int crn) {
        try {
            return cds.get(crn);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void readFile(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            if (data.length >= 5) {
                String id = data[0];
                int crn = Integer.parseInt(data[1]);
                int credits = Integer.parseInt(data[2]);
                String roomNum = data[3];
                String instructor = "";
                for (int i = 4; i < data.length; i++) {
                    instructor += data[i] + " ";
                }
                instructor = instructor.trim();
                add(id, crn, credits, roomNum, instructor);
            } else {
                System.out.println("Invalid line: " + line);
            }
        }
        scanner.close();
    }

    @Override
    public ArrayList<String> showAll() {
        return cds.showAll();
    }
}
