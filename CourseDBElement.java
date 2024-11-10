
/**
 * This class represents a course with its details.
 */
public class CourseDBElement implements Comparable<CourseDBElement> {
    private String courseID;
    private int crn;
    private int credits;
    private String roomNumber;
    private String instructor;

    public CourseDBElement() {
        this.courseID = "";
        this.crn = 0;
        this.credits = 0;
        this.roomNumber = "";
        this.instructor = "";
    }

    public CourseDBElement(String courseID, int crn, int credits, String roomNumber, String instructor) {
        this.courseID = courseID;
        this.crn = crn;
        this.credits = credits;
        this.roomNumber = roomNumber;
        this.instructor = instructor;
    }

    public String getCourseID() { return courseID; }
    public void setCourseID(String courseID) { this.courseID = courseID; }
    public int getCRN() { return crn; }
    public void setCRN(int crn) { this.crn = crn; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    @Override
    public int compareTo(CourseDBElement other) {
        return this.crn - other.crn;
    }

    @Override
    public String toString() {
        return "\nCourse:" + courseID + " CRN:" + crn + " Credits:" + credits + " Instructor:" + instructor + " Room:" + roomNumber;
    }
}
