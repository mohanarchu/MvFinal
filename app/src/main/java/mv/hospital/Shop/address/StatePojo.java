package mv.hospital.Shop.address;

public class StatePojo {
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
        private String CountryID;

        private String StateName;

        private String ID;

        private String displayorder;

        public String getCountryID ()
        {
            return CountryID;
        }

        public void setCountryID (String CountryID)
        {
            this.CountryID = CountryID;
        }

        public String getStateName ()
        {
            return StateName;
        }

        public void setStateName (String StateName)
        {
            this.StateName = StateName;
        }

        public String getID ()
        {
            return ID;
        }

        public void setID (String ID)
        {
            this.ID = ID;
        }

        public String getDisplayorder ()
        {
            return displayorder;
        }

        public void setDisplayorder (String displayorder)
        {
            this.displayorder = displayorder;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [CountryID = "+CountryID+", StateName = "+StateName+", ID = "+ID+", displayorder = "+displayorder+"]";
        }
    }


}
