import java.util.Comparator;

class AgeComparatorAsc implements Comparator<Student> {

    @Override
    public int compare(Student studentLeft, Student studentRight) {
        return studentLeft.getAge() - studentRight.getAge();
    }
}