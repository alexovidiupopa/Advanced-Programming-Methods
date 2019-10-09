package Repository;

import Model.GenericVehicle;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface{
    private List<GenericVehicle> repoList;
    //private GenericVehicle[] list;
    //private int length = 0;
    public Repository(){
        this.repoList = new ArrayList<GenericVehicle>();
    //    list = new GenericVehicle[10];
    }

    public void add(GenericVehicle vehicle){
        this.repoList.add(vehicle);
        //list[this.length++] = vehicle;
    }

    public void delete(int index){
        this.repoList.remove(index);
        /*int count=0;
        for(int i=0;i<this.length;i++)
            if (i==index)
                count++;
            else
                list[i-1]=list[i];
        */
    }


    public int getSize(){
       // return this.length;
        return this.repoList.size();
    }

    public List<GenericVehicle> getList(){
        return this.repoList;
    }
   // public GenericVehicle[] getList2(){return this.list;}
}
