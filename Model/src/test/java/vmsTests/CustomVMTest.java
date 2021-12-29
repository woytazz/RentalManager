package vmsTests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import vms.CustomVM;

import static org.junit.jupiter.api.Assertions.*;


public class CustomVMTest {

    @Test
    public void gettersTest() {
        CustomVM customVM = new CustomVM(1,"Fedora 33",21,16,1,false,"Nvidia", 3);
        assertEquals(customVM.getVMID(),1);
        assertEquals(customVM.getOS(),"Fedora 33");
        assertEquals(customVM.getDiskSize(),21);
        assertEquals(customVM.getThreads(),16);
        assertEquals(customVM.Price(),31);
        assertFalse(customVM.isRented());
        assertEquals(customVM.getGPU(), "Nvidia");
        assertEquals(customVM.getSnapshots(),3);
    }

    @Test
    public void getVMInfoTest() {
        CustomVM customVM = new CustomVM(1,"Fedora 33",21,16,1,false,"Nvidia", 3);

        String test = new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("VM ID", 1)
                .append("OS", "Fedora 33")
                .append("Disk size", 21)
                .append("Threads", 16)
                .append("Price", 31)
                .append("GPU", "Nvidia")
                .append("Snapshots", 3)
                .append("Rented", false)
                .toString();

        assertEquals(customVM.getVMInfo(), test);
    }

}
