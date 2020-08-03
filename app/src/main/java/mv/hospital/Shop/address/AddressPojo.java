package mv.hospital.Shop.address;

public class AddressPojo {
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
        private String address;

        private String city;

        private String phone;

        private String postalcode;

        private String name;

        private String customerid;

        private String state;

        private String addressid;

        private String primary;

        public String getAddress ()
        {
            return address;
        }

        public void setAddress (String address)
        {
            this.address = address;
        }

        public String getCity ()
        {
            return city;
        }

        public void setCity (String city)
        {
            this.city = city;
        }

        public String getPhone ()
        {
            return phone;
        }

        public void setPhone (String phone)
        {
            this.phone = phone;
        }

        public String getPostalcode ()
        {
            return postalcode;
        }

        public void setPostalcode (String postalcode)
        {
            this.postalcode = postalcode;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getCustomerid ()
        {
            return customerid;
        }

        public void setCustomerid (String customerid)
        {
            this.customerid = customerid;
        }

        public String getState ()
        {
            return state;
        }

        public void setState (String state)
        {
            this.state = state;
        }

        public String getAddressid ()
        {
            return addressid;
        }

        public void setAddressid (String addressid)
        {
            this.addressid = addressid;
        }

        public String getPrimary ()
        {
            return primary;
        }

        public void setPrimary (String primary)
        {
            this.primary = primary;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [address = "+address+", city = "+city+", phone = "+phone+", postalcode = "+postalcode+", name = "+name+", customerid = "+customerid+", state = "+state+", addressid = "+addressid+", primary = "+primary+"]";
        }
    }


}
