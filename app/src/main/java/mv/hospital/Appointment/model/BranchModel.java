package mv.hospital.Appointment.model;

public class BranchModel {
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
        private String Branch;

        private String BranchId;

        public String getBranch ()
        {
            return Branch;
        }

        public void setBranch (String Branch)
        {
            this.Branch = Branch;
        }

        public String getBranchId ()
        {
            return BranchId;
        }

        public void setBranchId (String BranchId)
        {
            this.BranchId = BranchId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [Branch = "+Branch+", BranchId = "+BranchId+"]";
        }
    }
}
