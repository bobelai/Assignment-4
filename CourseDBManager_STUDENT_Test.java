
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {
    private CourseDBManagerInterface dataMgr;

    @Before
    public void setUp() throws Exception {
        dataMgr = new CourseDBManager();
    }

    @After
    public void tearDown() throws Exception {
        dataMgr = null;
    }

    @Test
    public void testAddToDB() {
        try {
            dataMgr.add("CMSC150", 21551, 2, "Distance-Learning", "Alice A. Smith");
            dataMgr.add("CMSC200", 22345, 3, "SW220", "Bob B. Brown");
        } catch (Exception e) {
            fail("This should not have caused an Exception");
        }
        try {
            assertEquals("CMSC150", dataMgr.get(21551).getID());
            assertEquals("CMSC200", dataMgr.get(22345).getID());
        } catch (Exception e) {
            fail("Exception was thrown when accessing added courses");
        }
    }

    @Test
    public void testShowAll() {
        dataMgr.add("CMSC210", 21562, 2, "RM300", "Charlie C. Clark");
        dataMgr.add("CMSC220", 22975, 3, "Distance-Learning", "Dana D. Day");
        dataMgr.add("CMSC230", 21563, 4, "SC451", "Evan E. Evans");

        ArrayList<String> list = dataMgr.showAll();
        assertTrue(list.contains("\nCourse:CMSC230 CRN:21563 Credits:4 Instructor:Evan E. Evans Room:SC451"));
        assertTrue(list.contains("\nCourse:CMSC220 CRN:22975 Credits:3 Instructor:Dana D. Day Room:Distance-Learning"));
        assertTrue(list.contains("\nCourse:CMSC210 CRN:21562 Credits:2 Instructor:Charlie C. Clark Room:RM300"));
    }

    @Test
    public void testReadFile() {
        try {
            File inputFile = new File("StudentTestFile.txt");
            PrintWriter inFile = new PrintWriter(inputFile);
            inFile.println("CMSC240 21567 2 Distance-Learning Finn F. Fox");
            inFile.println("CMSC250 22349 3 SC450 Gina G. Green");
            inFile.close();

            dataMgr.readFile(inputFile);
            assertEquals("CMSC240", dataMgr.get(21567).getID());
            assertEquals("CMSC250", dataMgr.get(22349).getID());
            assertEquals("SC450", dataMgr.get(22349).getRoomNum());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not have been thrown");
        } catch (Exception e) {
            fail("An unexpected exception was thrown");
        }
    }
}
