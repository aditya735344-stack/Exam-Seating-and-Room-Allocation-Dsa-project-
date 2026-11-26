
    import java.util.*;

class Student {
    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }
}

class Room {
    String roomNo;
    int capacity;
    ArrayList<Student> students;

    Room(String roomNo, int capacity) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    boolean hasSpace() {
        return students.size() < capacity;
    }

    void addStudent(Student s) {
        if (hasSpace()) {
            students.add(s);
        }
    }
}

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();

        HashMap<Integer, String> allocation = new HashMap<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details of student " + (i + 1));

            System.out.print("Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            students.add(new Student(roll, name));
        }

        System.out.print("\nEnter number of rooms: ");
        int r = sc.nextInt();

        for (int i = 0; i < r; i++) {
            System.out.println("\nEnter details of room " + (i + 1));

            System.out.print("Room Number: ");
            String roomNo = sc.next();

            System.out.print("Room Capacity: ");
            int capacity = sc.nextInt();

            if (capacity <= 0) {
                System.out.println("Invalid capacity!");
                return;
            }

            rooms.add(new Room(roomNo, capacity));
        }

        students.sort(Comparator.comparingInt(s -> s.rollNo));


        Queue<Student> queue = new LinkedList<>(students);

        for (Room room : rooms) {
            int seatNo = 1;

            while (room.hasSpace() && !queue.isEmpty()) {
                Student s = queue.poll();

                room.addStudent(s);

                allocation.put(
                    s.rollNo,
                    "Room " + room.roomNo + ", Seat " + seatNo
                );

                seatNo++;
            }
        }


        System.out.println("\n===== EXAM SEATING ALLOCATION =====");

        for (Room room : rooms) {
            System.out.println("\nRoom: " + room.roomNo);
            System.out.println("Capacity: " + room.capacity);

            System.out.println("--------------------------------");
            System.out.printf("%-10s %-15s %-10s%n",
                    "Roll No", "Name", "Seat No");

            for (int i = 0; i < room.students.size(); i++) {
                Student s = room.students.get(i);

                System.out.printf("%-10d %-15s %-10d%n",
                        s.rollNo, s.name, i + 1);
            }
        }

        if (!queue.isEmpty()) {
            System.out.println("\nWARNING: " + queue.size()
                    + " students could not be allocated seats.");

            System.out.println("More room capacity is required.");
        } else {
            System.out.println("\nAll students allocated successfully!");
        }

        System.out.println("\n===== STUDENT ALLOCATION MAP =====");

        for (Student s : students) {
            System.out.println(
                    s.rollNo + " - " + s.name + " - "
                    + allocation.get(s.rollNo)
            );
        }

        sc.close();
    }
}

