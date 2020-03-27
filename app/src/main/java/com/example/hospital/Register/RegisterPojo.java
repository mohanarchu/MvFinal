package com.example.hospital.Register;

public class RegisterPojo {
    private Result[] result;

    private String otp;

    private String status;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

    public String getOtp ()
    {
        return otp;
    }

    public void setOtp (String otp)
    {
        this.otp = otp;
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
        return "ClassPojo [result = "+result+", otp = "+otp+", status = "+status+"]";
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
