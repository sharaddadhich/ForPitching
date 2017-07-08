package com.example.manoj.forpitching.Values;

/**
 * Created by Manoj on 7/1/2017.
 */

public class TotalDetails {
    String Name,PhoneNo,Email,Address,Fname,Mname,Gender;
    Integer Id;

    public TotalDetails(Integer id,String name, String phoneNo, String email, String address, String fname, String mname, String gender) {
        Id = id;
        Name = name;
        PhoneNo = phoneNo;
        Email = email;
        Address = address;
        Fname = fname;
        Mname = mname;
        Gender = gender;
    }

    public String getName() {
        return Name;
    }

    public Integer getId() {return  Id;}
    public String getPhoneNo() {
        return PhoneNo;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getFname() {
        return Fname;
    }

    public String getMname() {
        return Mname;
    }

    public String getGender() {
        return Gender;
    }
}
