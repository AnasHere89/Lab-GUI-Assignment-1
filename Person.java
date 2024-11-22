public class Person {
    private String name;
    private String fatherName;
    private String cnic;
    private String date;
    private String gender;
    private String city;
    private String imagePath;

    public Person(String name, String fatherName, String cnic, String date, String gender, String city, String imagePath) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.date = date;
        this.gender = gender;
        this.city = city;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Father Name: " + fatherName + ", CNIC: " + cnic + ", Date: " + date + ", Gender: " + gender + ", City: " + city + ", Image: " + imagePath;
    }
}