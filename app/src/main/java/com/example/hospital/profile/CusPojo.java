package com.example.hospital.profile;

public class CusPojo {
    private Result[] result;

    private String status;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", status = "+status+"]";
    }
    public class Result
    {
        private String country;

        private String address;

        private String gender;

        private String city;

        private String pass;

        private String otp;

        private String dob;

        private String name;

        private String blood_group;

        private String phone_number;

        private String state;

        private String customer_id;

        private String postal_code;

        private String email;

        public String getCountry ()
        {
            return country;
        }

        public void setCountry (String country)
        {
            this.country = country;
        }

        public String getAddress ()
        {
            return address;
        }

        public void setAddress (String address)
        {
            this.address = address;
        }

        public String getGender ()
        {
            return gender;
        }

        public void setGender (String gender)
        {
            this.gender = gender;
        }

        public String getCity ()
        {
            return city;
        }

        public void setCity (String city)
        {
            this.city = city;
        }

        public String getPass ()
    {
        return pass;
    }

        public void setPass (String pass)
        {
            this.pass = pass;
        }

        public String getOtp ()
        {
            return otp;
        }

        public void setOtp (String otp)
        {
            this.otp = otp;
        }

        public String getDob ()
        {
            return dob;
        }

        public void setDob (String dob)
        {
            this.dob = dob;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getBlood_group ()
        {
            return blood_group;
        }

        public void setBlood_group (String blood_group)
        {
            this.blood_group = blood_group;
        }

        public String getPhone_number ()
        {
            return phone_number;
        }

        public void setPhone_number (String phone_number)
        {
            this.phone_number = phone_number;
        }

        public String getState ()
        {
            return state;
        }

        public void setState (String state)
        {
            this.state = state;
        }

        public String getCustomer_id ()
        {
            return customer_id;
        }

        public void setCustomer_id (String customer_id)
        {
            this.customer_id = customer_id;
        }

        public String getPostal_code ()
        {
            return postal_code;
        }

        public void setPostal_code (String postal_code)
        {
            this.postal_code = postal_code;
        }

        public String getEmail ()
        {
            return email;
        }

        public void setEmail (String email)
        {
            this.email = email;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [country = "+country+", address = "+address+", gender = "+gender+", city = "+city+", pass = "+pass+", otp = "+otp+", dob = "+dob+", name = "+name+", blood_group = "+blood_group+", phone_number = "+phone_number+", state = "+state+", customer_id = "+customer_id+", postal_code = "+postal_code+", email = "+email+"]";
        }
    }


}
