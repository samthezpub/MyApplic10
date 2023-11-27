package edu.itstep.myapplic10;// Address.java
import androidx.room.Entity;

@Entity
public class Address {
    private String city;


    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    // Метод для создания объекта Address из строки
    public static Address fromString(String city) {
        Address address = new Address(city);
        return address;
    }

    // Метод для представления объекта Address в виде строки
    @Override
    public String toString() {
        return city;
    }

}
