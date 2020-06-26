package mv.hospital.Appointment.model;

public class AppointNumberPojo {
    private Result[] result;

    private String status;

    private Result1[] result1;

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

    public Result1[] getResult1 ()
    {
        return result1;
    }

    public void setResult1 (Result1[] result1)
    {
        this.result1 = result1;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", status = "+status+", result1 = "+result1+"]";
    }
    public class Result1
    {
        private String Appointmentid;

        public String getAppointmentid ()
        {
            return Appointmentid;
        }

        public void setAppointmentid (String Appointmentid)
        {
            this.Appointmentid = Appointmentid;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [Appointmentid = "+Appointmentid+"]";
        }
    }
    public class Result
    {
        private String RegistrationId;

        public String getRegistrationId ()
        {
            return RegistrationId;
        }

        public void setRegistrationId (String RegistrationId)
        {
            this.RegistrationId = RegistrationId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [RegistrationId = "+RegistrationId+"]";
        }
    }

}
