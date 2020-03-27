package com.example.hospital.Order;

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
        private String ID;

        public String getID ()
        {
            return ID;
        }

        public void setID (String ID)
        {
            this.ID = ID;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ID = "+ID+"]";
        }
    }


}
