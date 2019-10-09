package Repository;

import Model.GenericVehicle;

import java.util.List;

public interface RepositoryInterface {
    public void add(GenericVehicle vehicle);
    public void delete(int index);
    public int getSize();
    public List<GenericVehicle> getList();
}
