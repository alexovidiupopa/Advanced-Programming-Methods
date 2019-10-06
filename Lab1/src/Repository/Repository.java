package Repository;

import Model.GenericVehicle;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<GenericVehicle> repoList;

    public Repository(){
        this.repoList = new ArrayList<GenericVehicle>();
    }

    public void add(GenericVehicle vehicle){
        this.repoList.add(vehicle);
    }

    public void delete(int index){
        this.repoList.remove(index);
    }

    public int getSize(){
        return this.repoList.size();
    }

    public List<GenericVehicle> getList(){
        return this.repoList;
    }
}
