package vms;

public abstract class VM {

    private final int VMID;
    private final String OS;
    private final int diskSize;
    private final int threads;
    private final int basicPrice;
    private final boolean isRented;

    public VM(int ID, String OS, int diskSize, int threads, int basicPrice, boolean isRented) {
        this.VMID = ID;
        this.OS = OS;
        this.diskSize = diskSize;
        this.threads = threads;
        this.basicPrice = basicPrice;
        this.isRented = isRented;
    }


    public int getVMID() {
        return VMID;
    }

    public String getOS() {
        return OS;
    }

    public int getDiskSize() {
        return diskSize;
    }

    public int getThreads() {
        return threads;
    }

    public int getBasicPrice() {
        return basicPrice;
    }

    public boolean isRented() {
        return isRented;
    }

    public abstract int getSnapshots();

    public abstract String getGPU();

    public abstract String getVMInfo();

    public abstract int Price();

}
