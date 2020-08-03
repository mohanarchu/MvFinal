package mv.hospital.Appointment.model;

public class RoomPojo {
    private Rooms[] rooms;

    private Pickup[] pickup;

    private String status;

    public Rooms[] getRooms ()
    {
        return rooms;
    }

    public void setRooms (Rooms[] rooms)
    {
        this.rooms = rooms;
    }

    public Pickup[] getPickup ()
    {
        return pickup;
    }

    public void setPickup (Pickup[] pickup)
    {
        this.pickup = pickup;
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
        return "ClassPojo [rooms = "+rooms+", pickup = "+pickup+", status = "+status+"]";
    }
    public class Pickup
    {
        private String RoomTypeId;

        private String RoomType;

        private String AddedOn;



        private String BranchId;

        public String getRoomTypeId ()
        {
            return RoomTypeId;
        }

        public void setRoomTypeId (String RoomTypeId)
        {
            this.RoomTypeId = RoomTypeId;
        }

        public String getRoomType ()
        {
            return RoomType;
        }

        public void setRoomType (String RoomType)
        {
            this.RoomType = RoomType;
        }

        public String getAddedOn ()
        {
            return AddedOn;
        }

        public void setAddedOn (String AddedOn)
        {
            this.AddedOn = AddedOn;
        }



        public String getBranchId ()
        {
            return BranchId;
        }

        public void setBranchId (String BranchId)
        {
            this.BranchId = BranchId;
        }

    }

    public class Rooms
    {
        private String PickUp;

        private String AddedOn;


        private String BranchId;

        private String PickUpId;

        public String getPickUp ()
        {
            return PickUp;
        }

        public void setPickUp (String PickUp)
        {
            this.PickUp = PickUp;
        }

        public String getAddedOn ()
        {
            return AddedOn;
        }

        public void setAddedOn (String AddedOn)
        {
            this.AddedOn = AddedOn;
        }


        public String getBranchId ()
        {
            return BranchId;
        }

        public void setBranchId (String BranchId)
        {
            this.BranchId = BranchId;
        }

        public String getPickUpId ()
        {
            return PickUpId;
        }

        public void setPickUpId (String PickUpId)
        {
            this.PickUpId = PickUpId;
        }

    }

}
