package com.example.hospital.cart.orders;

public class OrderPojo {
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
        private String ShipContactNo;

        private String ShipName;

        private String Address;

        private String ShipZipCode;

        private String SubTotal;

        private String Name;

        private String ContactNo;

        private String TxnId;

        private String OrderAmount;

        private String ShipAddress;

        private String EmailId;

        private String ZipCode;

        private String ShipCity;

        private String ShipState;

        private String City;

        private String OrderId;

        private String OrderDate;

        private String addressid;

        private String TxnMsg;

        private String UserId;

        private String State;

        private String ShippingCost;

        private String Country;

        private String ShipCountry;

        private String ShipEmailId;

        private String TxnStatus;

        public String getShipContactNo ()
        {
            return ShipContactNo;
        }

        public void setShipContactNo (String ShipContactNo)
        {
            this.ShipContactNo = ShipContactNo;
        }

        public String getShipName ()
        {
            return ShipName;
        }

        public void setShipName (String ShipName)
        {
            this.ShipName = ShipName;
        }

        public String getAddress ()
        {
            return Address;
        }

        public void setAddress (String Address)
        {
            this.Address = Address;
        }

        public String getShipZipCode ()
        {
            return ShipZipCode;
        }

        public void setShipZipCode (String ShipZipCode)
        {
            this.ShipZipCode = ShipZipCode;
        }

        public String getSubTotal ()
        {
            return SubTotal;
        }

        public void setSubTotal (String SubTotal)
        {
            this.SubTotal = SubTotal;
        }

        public String getName ()
        {
            return Name;
        }

        public void setName (String Name)
        {
            this.Name = Name;
        }

        public String getContactNo ()
        {
            return ContactNo;
        }

        public void setContactNo (String ContactNo)
        {
            this.ContactNo = ContactNo;
        }

        public String getTxnId ()
        {
            return TxnId;
        }

        public void setTxnId (String TxnId)
        {
            this.TxnId = TxnId;
        }

        public String getOrderAmount ()
        {
            return OrderAmount;
        }

        public void setOrderAmount (String OrderAmount)
        {
            this.OrderAmount = OrderAmount;
        }

        public String getShipAddress ()
        {
            return ShipAddress;
        }

        public void setShipAddress (String ShipAddress)
        {
            this.ShipAddress = ShipAddress;
        }

        public String getEmailId ()
        {
            return EmailId;
        }

        public void setEmailId (String EmailId)
        {
            this.EmailId = EmailId;
        }

        public String getZipCode ()
        {
            return ZipCode;
        }

        public void setZipCode (String ZipCode)
        {
            this.ZipCode = ZipCode;
        }

        public String getShipCity ()
        {
            return ShipCity;
        }

        public void setShipCity (String ShipCity)
        {
            this.ShipCity = ShipCity;
        }

        public String getShipState ()
        {
            return ShipState;
        }

        public void setShipState (String ShipState)
        {
            this.ShipState = ShipState;
        }

        public String getCity ()
        {
            return City;
        }

        public void setCity (String City)
        {
            this.City = City;
        }

        public String getOrderId ()
        {
            return OrderId;
        }

        public void setOrderId (String OrderId)
        {
            this.OrderId = OrderId;
        }

        public String getOrderDate ()
        {
            return OrderDate;
        }

        public void setOrderDate (String OrderDate)
        {
            this.OrderDate = OrderDate;
        }

        public String getAddressid ()
    {
        return addressid;
    }

        public void setAddressid (String addressid)
        {
            this.addressid = addressid;
        }

        public String getTxnMsg ()
        {
            return TxnMsg;
        }

        public void setTxnMsg (String TxnMsg)
        {
            this.TxnMsg = TxnMsg;
        }

        public String getUserId ()
        {
            return UserId;
        }

        public void setUserId (String UserId)
        {
            this.UserId = UserId;
        }

        public String getState ()
        {
            return State;
        }

        public void setState (String State)
        {
            this.State = State;
        }

        public String getShippingCost ()
        {
            return ShippingCost;
        }

        public void setShippingCost (String ShippingCost)
        {
            this.ShippingCost = ShippingCost;
        }

        public String getCountry ()
        {
            return Country;
        }

        public void setCountry (String Country)
        {
            this.Country = Country;
        }

        public String getShipCountry ()
        {
            return ShipCountry;
        }

        public void setShipCountry (String ShipCountry)
        {
            this.ShipCountry = ShipCountry;
        }

        public String getShipEmailId ()
        {
            return ShipEmailId;
        }

        public void setShipEmailId (String ShipEmailId)
        {
            this.ShipEmailId = ShipEmailId;
        }

        public String getTxnStatus ()
        {
            return TxnStatus;
        }

        public void setTxnStatus (String TxnStatus)
        {
            this.TxnStatus = TxnStatus;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ShipContactNo = "+ShipContactNo+", ShipName = "+ShipName+", Address = "+Address+", ShipZipCode = "+ShipZipCode+", SubTotal = "+SubTotal+", Name = "+Name+", ContactNo = "+ContactNo+", TxnId = "+TxnId+", OrderAmount = "+OrderAmount+", ShipAddress = "+ShipAddress+", EmailId = "+EmailId+", ZipCode = "+ZipCode+", ShipCity = "+ShipCity+", ShipState = "+ShipState+", City = "+City+", OrderId = "+OrderId+", OrderDate = "+OrderDate+", addressid = "+addressid+", TxnMsg = "+TxnMsg+", UserId = "+UserId+", State = "+State+", ShippingCost = "+ShippingCost+", Country = "+Country+", ShipCountry = "+ShipCountry+", ShipEmailId = "+ShipEmailId+", TxnStatus = "+TxnStatus+"]";
        }
    }

}

