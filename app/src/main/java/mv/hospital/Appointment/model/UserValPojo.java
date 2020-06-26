package mv.hospital.Appointment.model;

public class UserValPojo {
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
        private String patientId;

        private String RegistrationId;

        public String getRegistrationId() {
            return RegistrationId;
        }

        public String getPatientId ()
        {
            return patientId;
        }

        public void setPatientId (String patientId)
        {
            this.patientId = patientId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [patientId = "+patientId+"]";
        }
    }


}
