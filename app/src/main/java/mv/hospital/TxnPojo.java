package mv.hospital;

public class TxnPojo {
    private String amount;

    private String field8;

    private String status;

    private String txnid;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getField8 ()
    {
        return field8;
    }

    public void setField8 (String field8)
    {
        this.field8 = field8;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getTxnid ()
    {
        return txnid;
    }

    public void setTxnid (String txnid)
    {
        this.txnid = txnid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", field8 = "+field8+", status = "+status+", txnid = "+txnid+"]";
    }
}
