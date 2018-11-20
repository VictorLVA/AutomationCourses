class Student {
    private int id;
    private String name;
    private String lastname;
    private int age;

    Student(int i) {
        this.id = i + 1;
        this.name = "Name" + (i + 1);
        this.lastname = (int) (Math.random() * 2) + "Lastname" + (i + 1);
        this.age = 16 + (int) (Math.random() * (100 - 16));
    }

    int getId() {
        return id;
    }

    String getLastname() {
        return lastname;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return id + "\t\t" +
                name + "\t\t" +
                lastname + "\t\t" +
                age + "\n";
    }
}
