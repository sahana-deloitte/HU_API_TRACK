package RestAssured;
//creating reuseable data
public class data {
    private String name;
    private String email;
    private String password;
    private int  age;
    private String description;
    public data(String name, String email,String password,int age)   //constructor overloading
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }
    public data(String email,String password) {

        this.email = email;
        this.password = password;
    }
    public data(String description){
        this.description=description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String  getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

