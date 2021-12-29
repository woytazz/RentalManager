package vms;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BasicVM extends VM {


    public BasicVM(int ID, String OS, int diskSize, int threads, int basicPrice, boolean isRented) {
        super(ID, OS, diskSize, threads, basicPrice, isRented);
    }

    @Override
    public int Price() {
        return getBasicPrice();
    }

    @Override
    public String getGPU() {
        return null;
    }

    @Override
    public int getSnapshots() {
        return 0;
    }

    @Override
    public String getVMInfo() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("VM ID", getVMID())
                .append("OS", getOS())
                .append("Disk size", getDiskSize())
                .append("Threads", getThreads())
                .append("Price", Price())
                .append("Rented", isRented())
                .toString();
    }

}
