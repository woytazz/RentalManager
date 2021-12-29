package vms;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomVM extends VM {

    private final String GPU;
    private final int snapshots;

    public CustomVM(int ID, String OS, int diskSize, int threads, int basicPrice, boolean isRented, String GPU, int snapshots) {
        super(ID, OS, diskSize, threads, basicPrice, isRented);
        this.GPU = GPU;
        this.snapshots = snapshots;
    }

    // Calculate price of CustomVM
    @Override
    public int Price() {
        int tempPrice = getBasicPrice();
        tempPrice += snapshots * 10;
        return tempPrice;
    }

    @Override
    public String getGPU() {
        return GPU;
    }

    @Override
    public int getSnapshots() {
        return snapshots;
    }

    @Override
    public String getVMInfo() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("VM ID", getVMID())
                .append("OS", getOS())
                .append("Disk size", getDiskSize())
                .append("Threads", getThreads())
                .append("Price", Price())
                .append("GPU", getGPU())
                .append("Snapshots", getSnapshots())
                .append("Rented", isRented())
                .toString();
    }

}
