package vmsTests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import vms.BasicVM;

import static org.junit.jupiter.api.Assertions.*;

public class BasicVMTest {

    @Test
    public void gettersTest() {
        BasicVM basicVM = new BasicVM(1,"Fedora 33",21,16,1,false);
        assertEquals(basicVM.getVMID(),1);
        assertEquals(basicVM.getOS(),"Fedora 33");
        assertEquals(basicVM.getDiskSize(),21);
        assertEquals(basicVM.getThreads(), 16);
        assertEquals(basicVM.Price(),1);
        assertFalse(basicVM.isRented());
    }

    @Test
    public void getVMInfoTest() {
        BasicVM basicVM = new BasicVM(1,"Fedora 33",21,16,1,false);

        String test = new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("VM ID", 1)
                .append("OS", "Fedora 33")
                .append("Disk size", 21)
                .append("Threads", 16)
                .append("Price", 1)
                .append("Rented", false)
                .toString();

        assertEquals(basicVM.getVMInfo(), test);
    }

}
