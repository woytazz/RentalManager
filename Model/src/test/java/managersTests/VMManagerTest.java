package managersTests;

import managers.VMManager;
import org.junit.jupiter.api.Test;
import vms.BasicVM;

import static org.junit.jupiter.api.Assertions.*;

public class VMManagerTest {

    @Test
    public void constructorTest() {
        VMManager vmManager = new VMManager();
        assertNotEquals(null, vmManager);
    }

    @Test
    public void addVMTest() {
        VMManager vmManager = new VMManager();
        BasicVM test_vm_1 = new BasicVM(1,"Fedora 33",1,1,1,false);
        BasicVM test_vm_2 = new BasicVM(1,"Fedora 33",1,1,1,false);
        vmManager.addVM(test_vm_1);
        vmManager.addVM(test_vm_2);
        vmManager.addVM(test_vm_1);
        assertNotEquals(vmManager.getVM(1),vmManager.getVM(2));
        assertEquals(vmManager.getVM(1),vmManager.getVM(1));
    }

}
