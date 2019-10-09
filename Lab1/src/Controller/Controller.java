package Controller;

import Model.GenericVehicle;

import Repository.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private RepositoryInterface repo = new Repository();

    public void add(GenericVehicle vehicle) throws Exception{
        if (vehicle.getPrice()<0) {
            throw new Exception("Price cannot be negative!");
        }
        this.repo.add(vehicle);
    }

    public void delete(int index) throws Exception{
        if (index>=this.repo.getSize() || index<0) {
            throw new Exception("Index not in [0, length) range.");
        }
        this.repo.delete(index);
    }

    public List<GenericVehicle> getAllVehicles(){
        return this.repo.getList();
    }

    public List<GenericVehicle> getVehiclesWithSpecialPrice(){
        List<GenericVehicle> resultList = new ArrayList<GenericVehicle>();
        for (GenericVehicle vehicle : this.repo.getList()){
            if (vehicle.getPrice()>1000) {
                resultList.add(vehicle);
            }
        }
        return resultList;
    }
}
