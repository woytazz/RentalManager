package managers;

import repositories.Repository;
import vms.VM;
import repositories.VMRepository;

import java.util.List;

public class VMManager {
    private final Repository<VM> vmRepository = new VMRepository();

    // Add VM to repository
    public void addVM(VM vm) {
        vmRepository.add(vm);
    }

    // Get VM from repository by ID
    public VM getVM(int index) {
        for (VM a : vmRepository.getElements()) {
            if (a.getVMID() == index) {
                return a;
            }
        }
        return null;
    }

    // Return list of VMs
    public List<VM> getVMList() {
        return vmRepository.getElements();
    }

}
